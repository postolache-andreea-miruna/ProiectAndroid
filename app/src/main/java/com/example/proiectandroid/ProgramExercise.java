package com.example.proiectandroid;

public class ProgramExercise {
    public String name;
    public String details;
    public int idExercise;

    public ProgramExercise(String name, String details, int idExercise) {
        this.name = name;
        this.details = details;
        this.idExercise = idExercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }
}
