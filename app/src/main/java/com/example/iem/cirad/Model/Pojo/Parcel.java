package com.example.iem.cirad.Model.Pojo;

/**
 * Created by iem on 05/01/2017.
 */

public class Parcel {

    private int Id;
    private String Name;
    private String Remark;
    private String Latitude;
    private String Longitude;
    private String Id_Farm;
    public Parcel()
    {

    }

    public Parcel(int id,String name) {
        Id = id;
        Name = name;
    }

    public Parcel(int id, String name,String remark,String latitude,String longitude,String id_Farm) {
        Id_Farm = id_Farm;
        Longitude = longitude;
        Latitude = latitude;
        Remark = remark;
        Name = name;
        Id = id;
    }

    public Parcel(int id, String name, String latitude, String longitude) {
        Id = id;
        Name = name;
        Latitude = latitude;
        Longitude = longitude;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
