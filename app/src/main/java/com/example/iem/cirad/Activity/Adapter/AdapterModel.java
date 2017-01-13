package com.example.iem.cirad.Activity.Adapter;

import com.example.iem.cirad.Model.Pojo.Parcel;
import com.example.iem.cirad.Model.Pojo.User;

/**
 * Created by iem on 12/01/2017.
 */

public class AdapterModel {
    private int id;
    private String name;
    private boolean isSelected;
    private User user;
    private Parcel parcel;

    public AdapterModel(int id, String name, Boolean isSelected,User user,Parcel parcel) {
        this.id = id;
        this.name = name;
        this.isSelected=isSelected;
        this.user=user;
        this.parcel= parcel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }
}
