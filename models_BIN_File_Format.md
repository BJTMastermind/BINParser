## BIN File Format (models.bin)

### Byte Order

The bin file format uses **Big Endian** Byte Order.

### Parsing BIN File (models.bin)

| Name | Size (in bytes) | Description |
| - | - | - |
| Version (Int) | 4 | The version of this models bin file. |
| ModelCount (Int) | 4 | The number of models in this bin file. |

Now loop the next section `ModelCount` times until you have parsed all models.

| Name | Size (in bytes) | Description |
| - | - | - |
| NameLength (Short) | 2 | The length of the name for this model. |
| Name (String) | Variable | The name of this model. Size is the result of NameLength. |
| TextureWidth (Int) | 4 | The width of the texture file for this entity. |
| TextureHeight (Int) | 4 | The height of the texture file for this entity. |
| PartCount (Int) | 4 | The number of parts the current model has. |

While inside the current loop, loop the next section `PartCount` times.

| Name | Size (in bytes) | Description |
| - | - | - |
| PartNameLength (Short) | 2 | The length of this parts name. |
| PartName (String) | Variable | The name of this part. |
| X (Float) | 4 | The X position of this part. |
| Y (Float) | 4 | The Y position of this part. |
| Z (Float) | 4 | The Z position of this part. |
| Yaw (Float) | 4 | The Yaw of this part. |
| Pitch (Float) | 4 | The Pitch of this part. |
| Roll (Float) | 4 | The Roll of this part. |

**If `Version` is greater then 0** do the next section otherwise skip past it.

| Name | Size (in bytes) | Description |
| - | - | - |
| Unknown1 (Int/Float) | 4 | -- |
| Unknown2 (Int/Float) | 4 | -- |
| Unknown3 (Int/Float) | 4 | -- |

**End of If**

| Name | Size (in bytes) | Description |
| - | - | - |
| BoxCount (Int) | 4 | The number of boxes in this part. |

While inside the current loop, loop the next section `BoxCount` times.

| Name | Size (in bytes) | Description |
| - | - | - |
| PosX (Float) | 4 | The X position of this box. |
| PosY (Float) | 4 | The Y position of this box. |
| PosZ (Float) | 4 | The Z position of this box. |
| SizeX (Int) | 4 | The size of this box on the X. |
| SizeY (Int) | 4 | The size of this box on the Y. |
| SizeZ (Int) | 4 | The size of this box on the Z. |
| U (Float) | 4 | The U position of this boxes UV. |
| V (Float) | 4 | The V position of this boxes UV. |
| Scale (Float) | 4 | The scale of this box. |
| Mirrored (Byte) | 1 | Weather this box is mirrored or not. |
