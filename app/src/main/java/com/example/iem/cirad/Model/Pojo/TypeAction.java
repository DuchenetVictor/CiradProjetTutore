package com.example.iem.cirad.Model.Pojo;

/**
 * Created by iem on 10/01/2017.
 */

public class TypeAction {
    private int Id;
    private String Name;

    public TypeAction(int id, String name)
    {
        this.Id = id;
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

}
