package com.example.proiectandroid;

import android.os.AsyncTask;

public class AsyncTaskIdFromEmail extends AsyncTask<String,Void, Integer> {
    FindIdForEmailOperation listener;

    public AsyncTaskIdFromEmail(FindIdForEmailOperation listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String emailul = strings[0];
        return MyApplication.getmAppDatabase().userDao().idFromEmail(emailul);
    }

    @Override
    protected void onPostExecute(Integer result) {//aici intoarcem daca a fost cu error sau success
        listener.idForEmail(result);
    }
}
