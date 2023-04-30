package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class AsyncTaskTrainingExercise extends AsyncTask<ModelExerciseTraining,Void, List<ProgramExercise>> {
    TrainingExerciseByIdProgram listener;

    public AsyncTaskTrainingExercise(TrainingExerciseByIdProgram listener) {
        this.listener = listener;
    }

    @Override
    protected List<ProgramExercise> doInBackground(ModelExerciseTraining... modelExerciseTrainings) {
        int programId = modelExerciseTrainings[0].programId;
        int dayId = modelExerciseTrainings[0].dayId;
        return MyApplication.getmAppDatabase().trainingDao().getExerciseDetailById(programId,dayId);
    }

    @Override
    protected void onPostExecute(List<ProgramExercise> programs) {
        listener.getAllExercisesIdProgramDay(programs);
    }
}
