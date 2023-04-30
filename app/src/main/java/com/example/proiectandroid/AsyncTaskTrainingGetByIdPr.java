package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class AsyncTaskTrainingGetByIdPr  extends AsyncTask<Integer,Void, List<ProgramDetails>> {
   TrainingGetAllByIdProgram listener;

    public AsyncTaskTrainingGetByIdPr(TrainingGetAllByIdProgram listener) {
        this.listener = listener;
    }

    @Override
    protected List<ProgramDetails> doInBackground(Integer... integers) {
        int idProgram = integers[0];
        return MyApplication.getmAppDatabase().trainingDao().getTrainingDetailById(idProgram);
    }

    @Override
    protected void onPostExecute(List<ProgramDetails> programs) {
        listener.getAllDaysIdProgram(programs);
    }
}
