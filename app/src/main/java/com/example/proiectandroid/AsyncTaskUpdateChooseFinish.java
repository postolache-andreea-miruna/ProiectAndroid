package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.Date;

public class AsyncTaskUpdateChooseFinish extends AsyncTask<ModelChooseFinishUpd, Void, String> {
ChooseUpdateFinishDate listener;

    public AsyncTaskUpdateChooseFinish(ChooseUpdateFinishDate listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(ModelChooseFinishUpd... modelChooseFinishUpds) {
        String email = modelChooseFinishUpds[0].emailUser;
        int idProgram = modelChooseFinishUpds[0].idProgram;
        try{
            MyApplication.getmAppDatabase().chooseDao().updateChooseByEmail(email,idProgram,new Date());
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String result) {//aici intoarcem daca a fost cu error sau success
        listener.updateFinishDate(result);
    }
}
