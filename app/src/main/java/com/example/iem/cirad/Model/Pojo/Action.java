package com.example.iem.cirad.Model.Pojo;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by iem on 05/01/2017.
 */

public class Action  {
    private int Id;
    private String Name;
    private int EmergencyLevel;
    private boolean isTreatment;
    private int TreatmentLevel;
    private String Remark;
    private Date DateMeasure;
    private int IdUser;

    public Action() {
    }

    public Action(String name, int emergencyLevel, boolean isTreatment, int treatmentLevel, String remark, Date dateMeasure, int idUser) {
        Name = name;
        EmergencyLevel = emergencyLevel;
        this.isTreatment = isTreatment;
        TreatmentLevel = treatmentLevel;
        Remark = remark;
        DateMeasure = dateMeasure;
        IdUser = idUser;
    }

    public Action(String name, int emergencyLevel, boolean isTreatment, String remark, Date dateMeasure, int idUser) {
        Name = name;
        EmergencyLevel = emergencyLevel;
        this.isTreatment = isTreatment;
        Remark = remark;
        DateMeasure = dateMeasure;
        IdUser = idUser;
    }

    public Action(int id, String name, int emergencyLevel, boolean isTreatment, int treatmentLevel, String remark, Date dateMeasure, int idUser) {
        Id = id;
        Name = name;
        EmergencyLevel = emergencyLevel;
        this.isTreatment = isTreatment;
        TreatmentLevel = treatmentLevel;
        Remark = remark;
        DateMeasure = dateMeasure;
        IdUser = idUser;
    }

    public Action(int id, String name, int emergencyLevel, boolean isTreatment, String remark, Date dateMeasure, int idUser) {
        Id = id;
        Name = name;
        EmergencyLevel = emergencyLevel;
        this.isTreatment = isTreatment;
        Remark = remark;
        DateMeasure = dateMeasure;
        IdUser = idUser;
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

    public int getEmergencyLevel() {
        return EmergencyLevel;
    }

    public void setEmergencyLevel(int emergencyLevel) {
        EmergencyLevel = emergencyLevel;
    }

    public boolean getIsTreatment() {
        return isTreatment;
    }

    public void setIsTreatment(boolean isTreatment) {
        this.isTreatment = isTreatment;
    }

    public int getTreatmentLevel() {
        return TreatmentLevel;
    }

    public void setTreatmentLevel(int treatmentLevel) {
        TreatmentLevel = treatmentLevel;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Date getDateMeasure() {
        return DateMeasure;
    }

    public void setDateMeasure(Date dateMeasure) {
        DateMeasure = dateMeasure;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }


    public ArrayList<String> inArray(){

        ArrayList<String> array = new ArrayList<>();
        if (Id == 0 && Name == "")
        {
            return null;
        }
        else
        {
            array.add(String.valueOf(Id));
            array.add(Name);
            array.add(String.valueOf(EmergencyLevel));
            array.add(String.valueOf(isTreatment));
            array.add(String.valueOf(TreatmentLevel));
            array.add(Remark);
            array.add(String.valueOf(DateMeasure));
            array.add((String.valueOf(IdUser)));

            return array;
        }
    }

}
