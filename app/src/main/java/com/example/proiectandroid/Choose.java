package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.Date;

@Entity(tableName = "choose",
        primaryKeys = {"idUser", "idProgram", "dateStart"},
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "idUser"),
                @ForeignKey(entity = Program.class, parentColumns = "id", childColumns = "idProgram")
        })
public class Choose {
    @ColumnInfo(name = "idUser")
    private int idUser;

    @ColumnInfo(name = "idProgram")
    private int idProgram;

    @ColumnInfo(name = "dateStart")
    @NonNull
    private Date dateStart;

    @Nullable
    @ColumnInfo(name = "dateFinish")
    private Date dateFinish;

    public Choose(int idUser, int idProgram, @NonNull Date dateStart, @Nullable Date dateFinish) {
        this.idUser = idUser;
        this.idProgram = idProgram;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Nullable
    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(@Nullable Date dateFinish) {
        this.dateFinish = dateFinish;
    }
}
