package com.example.proiectandroid;

import android.os.AsyncTask;
import android.util.Log;

public class InsertUserOperation extends AsyncTask<User, Void, String> {

    UserOperations listener;

    public InsertUserOperation(UserOperations listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(User... users) {//aici facem insert in BD
        try{
            MyApplication.getmAppDatabase().userDao().insert(users);
        }catch (Exception e){
            Log.d("exceptieeee", "email: " + e);
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result) {//aici intoarcem daca a fost cu error sau success
        listener.insertUser(result);
    }
}
