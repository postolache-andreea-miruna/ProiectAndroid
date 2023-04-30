package com.example.proiectandroid;

import android.os.AsyncTask;

import java.util.Date;

public class AsyncUpdateUser extends AsyncTask<UpdateUserModel,Void,String> {
    UserGetByEmailUpdateOperation listener;

    public AsyncUpdateUser(UserGetByEmailUpdateOperation listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(UpdateUserModel... updateUserModels) {
        UpdateUserModel model = new UpdateUserModel(updateUserModels[0].getEmail(),
                updateUserModels[0].getUserWeight(), updateUserModels[0].getUserHeight(),updateUserModels[0].getName());
        try{
            MyApplication.getmAppDatabase().userDao().updateUserByEmail(model.getEmail(),model.getUserWeight(),model.getUserHeight(),model.getName());
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    @Override
    protected void onPostExecute(String s) {
        listener.updateUser(s);
    }
}