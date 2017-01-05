package com.example.iem.cirad.Pojo;

import java.sql.Date;

/**
 * Created by iem on 05/01/2017.
 */

public class Action {
    private int id;
    private String Name;
    private int EmergencyLevel;
    private boolean isTraitment;
    private int TreatmentLevel;
    private String Remark;
    private Date DateMeasure;

    public Action(int id, String name, int emergencyLevel, boolean isTraitment, int treatmentLevel, String remark, Date dateMeasure) {
        this.id = id;
        Name = name;
        EmergencyLevel = emergencyLevel;
        this.isTraitment = isTraitment;
        TreatmentLevel = treatmentLevel;
        Remark = remark;
        DateMeasure = dateMeasure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isTraitment() {
        return isTraitment;
    }

    public void setTraitment(boolean traitment) {
        isTraitment = traitment;
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
}
