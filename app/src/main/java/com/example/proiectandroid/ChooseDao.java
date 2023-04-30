package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
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

    @Query("SELECT idProgram,program.name,no_days,outdoor,dateStart,dateFinish FROM choose " +
            "JOIN program ON program.id = choose.idProgram JOIN user ON idUser = user.id WHERE idUser = " +
            "(SELECT id FROM user WHERE user.email =:emailUser)")
    List<MyProgramsModel> getMyProgramsList(String emailUser);

}