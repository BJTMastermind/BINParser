package me.bjtmastermind.binparser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class MaterialsBINFile {
    private int materialCount;
    private LinkedHashMap<String, String> materials;
    private int materialsVersion;

    public MaterialsBINFile() {
        this.materials = new LinkedHashMap<>();
    }

    public MaterialsBINFile addMaterial(String name, String value) {
        this.materials.put(name, value);
        this.materialCount = this.materials.size();
        return this;
    }

    public void assemble(String outputFilepath) {
        DataOutputStream rawOutput = new DataOutputStream(new ByteArrayOutputStream());

        try {
            rawOutput.writeInt(materialsVersion);
            rawOutput.writeInt(materialCount);

            for (String name : materials.keySet()) {
                rawOutput.writeShort((short) name.length());
                rawOutput.writeUTF(name);
                rawOutput.writeShort((short) materials.get(name).length());
                rawOutput.writeUTF(materials.get(name));
            }

            File output = new File(outputFilepath);
            Files.write(output.toPath(), Utils.dataOutputStreamToArray(rawOutput));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaterialCount() {
        return this.materialCount;
    }

    public LinkedHashMap<String, String> getMaterials() {
        return this.materials;
    }

    public int getMaterialVersion() {
        return this.materialsVersion;
    }

    public MaterialsBINFile parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(new File(filepath).toURI()));

        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);

        int version = buffer.getInt();
        int materialCount = buffer.getInt();
        this.materialsVersion = version;
        this.materialCount = materialCount;

        for (int i = 0; i < materialCount; i++) {
            short nameLength = buffer.getShort();
            String name = Utils.getString(buffer, nameLength);
            short valueLength = buffer.getShort();
            String value = Utils.getString(buffer, valueLength);

            this.materials.put(name, value);
        }
        return this;
    }

    public String printMaterials() {
        String output = "";
        for (String name : this.materials.keySet()) {
            output += name+" | "+this.materials.get(name)+"\n";
        }
        return output;
    }

    public MaterialsBINFile setMaterials(LinkedHashMap<String, String> materials) {
        this.materials = materials;
        this.materialCount = materials.size();
        return this;
    }

    public MaterialsBINFile setMaterialVersion(int version) {
        this.materialsVersion = version;
        return this;
    }
}
