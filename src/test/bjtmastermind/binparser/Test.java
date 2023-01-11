package test.bjtmastermind.binparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import me.bjtmastermind.binparser.MaterialsBINFile;
import me.bjtmastermind.binparser.ModelsBINFile;

public class Test {

    public static void main(String[] args) throws IOException {
        materialsParsingTest("path/to/entityMaterials.bin");
        materialsAssembleTest("path/to/entityMaterials.bin", "path/to/new_entityMaterials.bin");

        modelsParsingTest("path/to/models.bin");
        modelsAssembleTest("path/to/models.bin", "path/to/new_models.bin");
    }

    private static void materialsParsingTest(String inputPath) throws IOException {
        MaterialsBINFile materialsBINFile = new MaterialsBINFile();
        materialsBINFile.parse(inputPath);

        System.out.println(materialsBINFile.printMaterials());
    }

    private static void materialsAssembleTest(String inputPath, String outputPath) throws IOException {
        MaterialsBINFile materialsBINFile = new MaterialsBINFile();
        materialsBINFile.parse(inputPath);

        materialsBINFile.assemble(outputPath);

        if (Files.exists(new File(outputPath).toPath())) {
            System.out.println("Successfully created file at: '"+outputPath+"'.");
        } else {
            System.out.println("Failed to create entityMaterials file.");
        }
    }

    private static void modelsParsingTest(String inputPath) throws IOException {
        ModelsBINFile modelsBINFile = new ModelsBINFile();
        modelsBINFile.parse(inputPath);

        System.out.println(modelsBINFile.printModels());
    }

    private static void modelsAssembleTest(String inputPath, String outputPath) throws IOException {
        ModelsBINFile modelsBINFile = new ModelsBINFile();
        modelsBINFile.parse(inputPath);

        modelsBINFile.assemble(outputPath);

        if (Files.exists(new File(outputPath).toPath())) {
            System.out.println("Successfully created file at: '"+outputPath+"'.");
        } else {
            System.out.println("Failed to create models file.");
        }
    }
}
