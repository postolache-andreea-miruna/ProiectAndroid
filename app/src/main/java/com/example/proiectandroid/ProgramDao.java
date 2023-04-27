package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProgramDao {
    @Insert
    void insertProgram(Program program);

    @Update
    void updateProgram(Program program);

    @Delete
    void deleteProgram(Program program);

    @Query("SELECT * FROM program")
    List<Program> getPrograms();
}
