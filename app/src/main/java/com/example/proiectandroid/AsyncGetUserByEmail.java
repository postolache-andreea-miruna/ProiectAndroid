package com.example.proiectandroid;

import android.os.AsyncTask;

public class AsyncGetUserByEmail extends AsyncTask<String,Void,GetUserByEmailModel> {
    UserGetByEmailUpdateOperation listener;

    public AsyncGetUserByEmail(UserGetByEmailUpdateOperation listener) {
        this.listener = listener;
    }
    @Override
    protected GetUserByEmailModel doInBackground(String... strings) {
        String email = strings[0];
        return MyApplication.getmAppDatabase().userDao().getUserByEmail(email);
    }

    @Override
    protected void onPostExecute(GetUserByEmailModel result) {
        listener.geUserByEmail(result);
    }
}
