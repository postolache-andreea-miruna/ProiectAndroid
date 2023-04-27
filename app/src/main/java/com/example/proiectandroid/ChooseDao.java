package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChooseDao {
    @Insert
    void insertChoose(Choose choose);

    @Update
    void updateChoose(Choose choose);

    @Delete
    void deleteChoose(Choose choose);

    @Query("SELECT * FROM choose")
    List<Choose> getChooseList();
}