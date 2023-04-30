package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.List;

public class GetAllProgramsOperation extends AsyncTask<Void,Void, List<ProgramModel>> {

    ProgramOperation listener;

    public GetAllProgramsOperation(ProgramOperation listener) {
        this.listener = listener;
    }

    @Override
    protected List<ProgramModel> doInBackground(Void... voids) {
        return MyApplication.getmAppDatabase().programDao().getPrograms();
    }

    @Override
    protected void onPostExecute(List<ProgramModel> programs) {
         listener.getAllPrograms(programs);
    }
}
