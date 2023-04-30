package com.example.proiectandroid;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskChooseInsert extends AsyncTask<Choose, Void, String> {
    ChooseOperationInsert listener;

    public AsyncTaskChooseInsert(ChooseOperationInsert listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Choose... chooses) {
        try{
            MyApplication.getmAppDatabase().chooseDao().insertChoose(chooses[0]);
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result) {//aici intoarcem daca a fost cu error sau success
        listener.insertChoose(result);
    }
}
