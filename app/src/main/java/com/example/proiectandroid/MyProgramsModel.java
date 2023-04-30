package com.example.proiectandroid;

import java.util.Date;

public class MyProgramsModel {
    private int idProgram;
    private String name;

    public MyProgramsModel(int idProgram, String name, int no_days, boolean outdoor, Date dateStart, Date dateFinish) {
        this.idProgram = idProgram;
        this.name = name;
        this.no_days = no_days;
        this.outdoor = outdoor;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    private int no_days;
    private boolean outdoor;
    private Date dateStart;
    private Date dateFinish;

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_days() {
        return no_days;
    }

    public void setNo_days(int no_days) {
        this.no_days = no_days;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
}
