package com.example.proiectandroid;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DayDao {
    @Insert
    void insertDay(Day day);

    @Update
    void updateDay(Day day);

    @Delete
    void deleteDay(Day day);

    @Query("SELECT * FROM day")
    List<Day> getDays();
}
