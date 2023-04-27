package com.example.proiectandroid;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "training",
        primaryKeys = {"idProgram", "idDay", "idExercise"},
        foreignKeys = {
                @ForeignKey(entity = Program.class, parentColumns = "id", childColumns = "idProgram"),
                @ForeignKey(entity = Day.class, parentColumns = "id", childColumns = "idDay"),
                @ForeignKey(entity = Exercise.class, parentColumns = "id", childColumns = "idExercise")
        })
public class Training {
    @ColumnInfo(name = "idProgram")
    private int idProgram;

    @ColumnInfo(name = "idDay")
    private int idDay;

    @ColumnInfo(name = "idExercise")
    private int idExercise;

    public Training(int idProgram, int idDay, int idExercise) {
        this.idProgram = idProgram;
        this.idDay = idDay;
        this.idExercise = idExercise;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public int getIdDay() {
        return idDay;
    }

    public void setIdDay(int idDay) {
        this.idDay = idDay;
    }

    public int getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(int idExercise) {
        this.idExercise = idExercise;
    }
}
