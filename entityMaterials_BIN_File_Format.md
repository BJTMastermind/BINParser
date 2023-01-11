## Bin File Format (entityMaterials.bin)

### Byte Order

The bin file format uses **Big Endian** Byte Order.

### Parsing

| Name | Size (in bytes) | Description |
| - | - | - |
| Version (Int) | 4 | The version of this models bin file. |
| MaterialCount (Int) | 4 | The number of models in this bin file. |

Now loop the next section `MaterialCount` times until you have parsed all materials.

| Name | Size (in bytes) | Description |
| - | - | - |
| NameLength (Short) | 2 | The length of the name of this entity. |
| Name (String) | Variable | The name of this entity. |
| MaterialNameLength (Short) | 2 | The length of the material name for this entity. |
| MaterialName (String) | Variable | The material name for this entity. |
