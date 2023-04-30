package com.example.proiectandroid;

public class ProgramDetails {
    public int id;
    public int idProgram;
    public String name;

    public ProgramDetails(int id, int idProgram, String name) {
        this.id = id;
        this.idProgram = idProgram;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
