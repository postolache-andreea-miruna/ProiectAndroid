package com.example.proiectandroid;

public class ProgramModel {
    private int id;
    private String name;
    private int no_days;
    private boolean outdoor;

    public ProgramModel(int id, String name, int no_days, boolean outdoor) {
        this.id = id;
        this.name = name;
        this.no_days = no_days;
        this.outdoor = outdoor;
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
}
