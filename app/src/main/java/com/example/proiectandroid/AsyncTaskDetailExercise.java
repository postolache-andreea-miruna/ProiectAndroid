package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class AsyncTaskDetailExercise extends AsyncTask<Integer,Void,String> {
    GetDetailsExerciseOperation listener;

    public AsyncTaskDetailExercise(GetDetailsExerciseOperation listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        int idExercise = integers[0];
        return MyApplication.getmAppDatabase().exerciseDao().getDetailForExerciseId(idExercise);
    }

    @Override
    protected void onPostExecute(String detail) {
        listener.getDetailExercise(detail);
    }
}
