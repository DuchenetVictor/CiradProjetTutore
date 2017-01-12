package com.example.iem.cirad.Model.Pojo;

import java.sql.Blob;

/**
 * Created by iem on 05/01/2017.
 */

public class Picture {

    private int Id;
    private Blob BlobPicture;

    public Picture() {
    }

    public Picture(int id, Blob blobPicture) {
        Id = id;
        BlobPicture = blobPicture;
    }

    public int getId() {
        return Id;
    }

    public Blob getBlobPicture() {
        return BlobPicture;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setBlobPicture(Blob blobPicture) {
        BlobPicture = blobPicture;
    }
}
