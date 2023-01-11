package me.bjtmastermind.binparser.modelStorage;

public class Box {
    public float x;
    public float y;
    public float z;
    public int width;
    public int height;
    public int length;
    public float u;
    public float v;
    public float scale;
    public boolean mirror;

    public Box(float x, float y, float z, int width, int height, int length, float u, float v, float scale, boolean mirror) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.length = length;
        this.u = u;
        this.v = v;
        this.scale = scale;
        this.mirror = mirror;
    }
}
