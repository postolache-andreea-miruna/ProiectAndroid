package com.example.proiectandroid;

import android.os.AsyncTask;
import android.util.Log;

public class FindLoginUserOperation extends AsyncTask<EmailPassw,Void, Integer> {
    UserOperationLogin listener;

    public FindLoginUserOperation(UserOperationLogin listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(EmailPassw... emailPassws) {
        String emailul = emailPassws[0].email;
        String password = emailPassws[0].passw;

        return MyApplication.getmAppDatabase().userDao().login(emailul,password);
    }


        @Override
        protected void onPostExecute(Integer result) {//aici intoarcem daca a fost cu error sau success
            listener.userHasAccount(result);
        }
}
