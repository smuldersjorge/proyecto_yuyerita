package net.smulders.tesis.yuyeritaandroid;

public class Indication {
    public final String id;
    public final String name;
    public final String description;
    public Indication(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
