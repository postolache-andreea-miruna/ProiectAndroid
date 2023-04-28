package com.example.proiectandroid;

import android.os.AsyncTask;

public class FindLoginGoogleOperation extends AsyncTask<String,Void, Integer> {
    UserOperationLoginGoogle listener;

    public FindLoginGoogleOperation(UserOperationLoginGoogle listener) {
        this.listener = listener;
    }


    @Override
    protected Integer doInBackground(String... strings) {
        String emailul = strings[0];
        return MyApplication.getmAppDatabase().userDao().existGoogleAccount(emailul);
    }

    @Override
    protected void onPostExecute(Integer result) {//aici intoarcem daca a fost cu error sau success
        listener.userHasGoogleAccount(result);
    }
}