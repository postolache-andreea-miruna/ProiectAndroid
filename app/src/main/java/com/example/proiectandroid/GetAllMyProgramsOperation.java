package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class GetAllMyProgramsOperation extends AsyncTask<String,Void, List<MyProgramsModel>> {
    MyProgramOperation listener;

    public GetAllMyProgramsOperation(MyProgramOperation listener) {
        this.listener = listener;
    }

    @Override
    protected List<MyProgramsModel> doInBackground(String... strings) {
        String email = strings[0];
        return MyApplication.getmAppDatabase().chooseDao().getMyProgramsList(email);
    }

    @Override
    protected void onPostExecute(List<MyProgramsModel> programs) {
        listener.getAllMyPrograms(programs);
    }
}
