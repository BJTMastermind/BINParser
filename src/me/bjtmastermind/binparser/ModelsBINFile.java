package me.bjtmastermind.binparser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import me.bjtmastermind.binparser.modelStorage.Box;
import me.bjtmastermind.binparser.modelStorage.Model;
import me.bjtmastermind.binparser.modelStorage.Part;

public class ModelsBINFile {
    private int modelCount;
    private int modelsVersion;
    private ArrayList<Model> models;

    public ModelsBINFile() {
        this.models = new ArrayList<>();
    }

    public ModelsBINFile addModel(Model model) {
        this.models.add(model);
        this.modelCount = models.size();
        return this;
    }

    public void assemble(String outputFilepath) {
        DataOutputStream rawOutput = new DataOutputStream(new ByteArrayOutputStream());

        try {
            rawOutput.writeInt(this.modelsVersion);
            rawOutput.writeInt(this.modelCount);

            for (Model model : this.models) {
                rawOutput.writeShort((short) model.getName().length());
                rawOutput.writeUTF(model.getName());
                rawOutput.writeInt(model.textureWidth);
                rawOutput.writeInt(model.textureHeight);
                rawOutput.writeInt(model.getParts().size());

                for (Part part : model.getParts()) {
                    rawOutput.writeShort((short) part.name.length());
                    rawOutput.writeUTF(part.name);
                    rawOutput.writeFloat(part.x);
                    rawOutput.writeFloat(part.y);
                    rawOutput.writeFloat(part.z);
                    rawOutput.writeFloat(part.yaw);
                    rawOutput.writeFloat(part.pitch);
                    rawOutput.writeFloat(part.roll);

                    if (this.modelsVersion > 0) {
                        rawOutput.writeInt(0);
                        rawOutput.writeInt(0);
                        rawOutput.writeInt(0);
                    }
                    rawOutput.writeInt(part.getBoxes().size());

                    for (Box box : part.getBoxes()) {
                        rawOutput.writeFloat(box.x);
                        rawOutput.writeFloat(box.y);
                        rawOutput.writeFloat(box.z);
                        rawOutput.writeInt(box.width);
                        rawOutput.writeInt(box.height);
                        rawOutput.writeInt(box.length);
                        rawOutput.writeFloat(box.u);
                        rawOutput.writeFloat(box.v);
                        rawOutput.writeFloat(box.scale);
                        rawOutput.writeBoolean(box.mirror);
                    }
                }
            }

            File output = new File(outputFilepath);
            Files.write(output.toPath(), Utils.dataOutputStreamToArray(rawOutput));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getModelCount() {
        return this.modelCount;
    }

    public ArrayList<Model> getModels() {
        return this.models;
    }

    public int getModelsVersion() {
        return this.modelsVersion;
    }

    public ModelsBINFile parse(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(new File(filepath).toURI()));

        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN);

        int version = buffer.getInt();
        int modelCount = buffer.getInt();
        this.modelsVersion = version;
        this.modelCount = modelCount;

        for (int i = 0; i < modelCount; i++) {
            short nameLength = buffer.getShort();
            String name = Utils.getString(buffer, nameLength);
            int textureWidth = buffer.getInt();
            int textureHeight = buffer.getInt();
            int partCount = buffer.getInt();

            Model model = new Model(name, textureWidth, textureHeight);

            for (int j = 0; j < partCount; j++) {
                short partNameLength = buffer.getShort();
                String partName = Utils.getString(buffer, partNameLength);
                float x = buffer.getFloat();
                float y = buffer.getFloat();
                float z = buffer.getFloat();
                float yaw = buffer.getFloat();
                float pitch = buffer.getFloat();
                float roll = buffer.getFloat();

                Part part = new Part(partName, x, y, z, yaw, pitch, roll);

                if (version > 0) {
                    buffer.getInt(); // Skip past Unknown/Unused value
                    buffer.getInt(); // Skip past Unknown/Unused value
                    buffer.getInt(); // Skip past Unknown/Unused value
                }
                int boxCount = buffer.getInt();

                for (int k = 0; k < boxCount; k++) {
                    float posX = buffer.getFloat();
                    float posY = buffer.getFloat();
                    float posZ = buffer.getFloat();
                    int sizeX = buffer.getInt();
                    int sizeY = buffer.getInt();
                    int sizeZ = buffer.getInt();
                    float u = buffer.getFloat();
                    float v = buffer.getFloat();
                    float scale = buffer.getFloat();
                    boolean mirrored = buffer.get() == 1;

                    Box box = new Box(posX, posY, posZ, sizeX, sizeY, sizeZ, u, v, scale, mirrored);

                    part.addBox(box);
                }
                model.addPart(part);
            }
            models.add(model);
        }
        return this;
    }

    public String printModels() {
        String output = "";
        for (Model model : this.models) {
            output += model.getName() + " {";
            if (model.getParts().size() > 0) {
                output += "\n    parts {";
                for (Part part : model.getParts()) {
                    output += "\n        [";
                    output += "\n            name="+part.name;
                    output += "\n            x="+part.x;
                    output += "\n            y="+part.y;
                    output += "\n            z="+part.z;
                    output += "\n            yaw="+part.yaw;
                    output += "\n            pitch="+part.pitch;
                    output += "\n            roll="+part.roll;
                    if (part.getBoxes().size() > 0) {
                        output += "\n            boxes {";
                        for (Box box : part.getBoxes()) {
                            output += "\n                [";
                            output += "\n                    x="+box.x;
                            output += "\n                    y="+box.y;
                            output += "\n                    z="+box.z;
                            output += "\n                    width="+box.width;
                            output += "\n                    height="+box.height;
                            output += "\n                    length="+box.length;
                            output += "\n                    u="+box.u;
                            output += "\n                    v="+box.v;
                            output += "\n                    scale="+box.scale;
                            output += "\n                    mirror="+box.mirror;
                            output += "\n                ]";
                        }
                        output += "\n            }";
                        output += "\n        ]";
                    } else {
                        output += "\n        ]";
                    }
                }
                output += "\n    }";
            }
            output += "\n}\n";
        }
        return output;
    }

    public ModelsBINFile setModels(ArrayList<Model> models) {
        this.models = models;
        this.modelCount = models.size();
        return this;
    }

    public ModelsBINFile setModelsVersion(int version) {
        this.modelsVersion = version;
        return this;
    }
}
