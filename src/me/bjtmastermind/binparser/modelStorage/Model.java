package me.bjtmastermind.binparser.modelStorage;

import java.util.ArrayList;

public class Model {
    private String name;
    public int textureWidth;
    public int textureHeight;
    private ArrayList<Part> parts = new ArrayList<>();

    public Model(String name, int textureWidth, int textureHeight) {
        this.name = name;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public void addPart(Part part) {
        this.parts.add(part);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Part> getParts() {
        return this.parts;
    }
}
