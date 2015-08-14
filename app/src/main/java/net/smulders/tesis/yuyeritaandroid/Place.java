package net.smulders.tesis.yuyeritaandroid;

public class Place {
    public final String id;
    public final String name;
    public final String address;
    public final String gps_lat;
    public final String gps_lon;
    public final String phone;
    public Place(String id, String name, String address, String gps_lat, String gps_lon, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.gps_lat = gps_lat;
        this.gps_lon = gps_lon;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name;
    }
}
