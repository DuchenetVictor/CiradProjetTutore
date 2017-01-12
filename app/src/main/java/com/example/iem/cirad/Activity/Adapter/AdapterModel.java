package com.example.iem.cirad.Activity.Adapter;

/**
 * Created by iem on 12/01/2017.
 */

public class AdapterModel {
    private int idTypeAction;
    private String name;
    private boolean isSelected;

    public AdapterModel(int idTypeAction, String name, Boolean isSelected) {
        this.idTypeAction = idTypeAction;
        this.name = name;
        this.isSelected=isSelected;
    }

    public int getIdTypeAction() {
        return idTypeAction;
    }

    public void setIdTypeAction(int idTypeAction) {
        this.idTypeAction = idTypeAction;
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
}
