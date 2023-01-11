package me.bjtmastermind.binparser.modelStorage;

import java.util.ArrayList;

public class Part {
    public String name;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float pitch;
    public float roll;
    private ArrayList<Box> boxes = new ArrayList<>();

    public Part(String name, float x, float y, float z, float yaw, float pitch, float roll) {
        this(name);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public Part(String name) {
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.yaw = 0;
        this.pitch = 0;
        this.roll = 0;
    }

    public void addBox(Box box) {
        this.boxes.add(box);
    }

    public ArrayList<Box> getBoxes() {
        return this.boxes;
    }
}
