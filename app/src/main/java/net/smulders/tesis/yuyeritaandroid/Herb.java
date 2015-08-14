package net.smulders.tesis.yuyeritaandroid;

public class Herb {
    public final String id;
    public final String name;
    public final String properties;
    public final String posology;
    public final String image;
    public Herb(String id, String name, String properties, String posology, String image){
        this.id = id;
        this.name = name;
        this.properties = properties;
        this.posology = posology;
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
