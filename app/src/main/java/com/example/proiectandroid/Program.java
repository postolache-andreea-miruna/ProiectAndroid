package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "program")
public class Program {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "no_days")
    private int noDays;

    @NonNull
    @ColumnInfo(name = "outdoor")
    private boolean outdoor;

    public Program(int id, @NonNull String name, int noDays, boolean outdoor) {
        this.id = id;
        this.name = name;
        this.noDays = noDays;
        this.outdoor = outdoor;
    }

    public int getId() {
        return id;
    }



    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getNoDays() {
        return noDays;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }
}