package net.smulders.tesis.yuyeritaandroid;

public class Ingest {
    public final String id;
    public final String name;
    public Ingest(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
