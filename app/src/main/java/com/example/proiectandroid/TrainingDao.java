package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrainingDao {
    @Insert
    void insertTraining(Training training);

    @Update
    void updateTraining(Training training);

    @Delete
    void deleteTraining(Training training);

    @Query("SELECT DISTINCT day.name,day.id,idProgram FROM training JOIN day ON training.idDay = day.id JOIN exercise ON training.idExercise = exercise.id JOIN program ON training.idProgram = program.id WHERE program.id =:programId ")
    List<ProgramDetails> getTrainingDetailById(int programId);

    @Query("SELECT exercise.name,details,idExercise FROM training JOIN day ON training.idDay = day.id JOIN exercise ON training.idExercise = exercise.id JOIN program ON training.idProgram = program.id WHERE program.id =:programId AND day.id =:dayId")
    List<ProgramExercise> getExerciseDetailById(int programId,int dayId);
}
