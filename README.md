# BINParser

A small Java library for parsing .bin files used by Minecraft: Legacy Console Edition into a easy to use format.
This library supports reading and writing of both `entityMaterials.bin` and `models.bin` files.

### How To Use

* Download `BINParser.jar` from [Releases](https://github.com/BJTMastermind/BINParser/releases) tab.
* Add the library into your project.
* Import `me.bjtmastermind.binparser.MaterialsBINFile` and/or `me.bjtmastermind.binparser.ModelsBINFile` to use.

Example Code (Parsing `entityMaterials.bin`)

```java
MaterialBINFile materialBINFile = new MaterialBINFile();
materialBINFile.parse("path/to/entityMaterials.bin");

System.out.println(materialBINFile);
```

Output

```
blaze_head | entity_emissive_alpha
boat | entity_alphatest
creeper | entity_alphatest
chicken | entity_alphatest
dolphin | entity_alphatest
...
```

Example Code (Assembling `entityMaterials.bin`)

```java
MaterialsBINFile materialBINFile = new MaterialsBINFile();
materialsBINFile.parse("path/to/materials.bin");

materialsBINFile.assemble("path/to/new_entityMaterials.bin");
```

Output

```
Successfully created file at: 'path/to/new_entityMaterials.bin'.
```

Example Code (Parsing `models.bin`)

```java
ModelsBINFile modelsBINFile = new ModelsBINFile();
modelsBINFile.parse("path/to/models.bin");

System.out.println(modelsBINFile);
```

Output

```
bat {
    parts {
        [
            name=head
            x=0.0
            y=0.0
            z=0.0
            yaw=0.0
            pitch=0.0
            roll=0.0
        ]
        ...
    }
    ...
}
...
```

Example Code (Assembling `models.bin`)

```java
ModelsBINFile materialBINFile = new ModelsBINFile();
modelsBINFile.parse("path/to/models.bin");

modelsBINFile.assemble("path/to/new_models");
```

Output

```
Successfully created file at: 'path/to/new_models.bin'.
```

### Minimum Java Version

* Java 8

### BIN File Format

* See [entityMaterials_BIN_File_Format.md](./entityMaterials_BIN_File_Format.md)
* See [models_BIN_File_Format.md](./models_BIN_File_Format.md)
