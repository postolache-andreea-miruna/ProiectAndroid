package com.example.proiectandroid;

import static android.content.Context.BATTERY_SERVICE;
import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class UserProfileFragment extends Fragment implements  UserGetByEmailUpdateOperation{
    public UserProfileFragment(){
        super(R.layout.fragment_user_profile);
    }

    private TextView name;
    private TextView weight;
    private TextView height;

    private ImageView profilePicture;
    private Button editButton;
    private String emailUser;
    private GetUserByEmailModel userModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.profile_name);
        weight = view.findViewById(R.id.profile_weight);
        height = view.findViewById(R.id.profile_height);

        profilePicture = view.findViewById(R.id.profile_ImageView);
        editButton = view.findViewById(R.id.profile_edit_button);

        //trebuie facuta o fct de get care ia date precum: username,profilePicture,weight,height
        Context context = getContext();
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        emailUser = preferences.getString(PREFERENCES_ID_KEY,"");
        userByEmail(emailUser);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name_u = name.getText().toString();
               float weight_u = Float.parseFloat(weight.getText().toString());
               float height_u = Float.parseFloat(height.getText().toString());

                updateTheUser(emailUser,weight_u,height_u,name_u);
            }
        });
    }

    private void userByEmail(String emailUser){
        new AsyncGetUserByEmail(this).execute(emailUser);
    }
    private void updateTheUser(String emailUser,float u_weight,float u_height,String u_name){

        UpdateUserModel model = new UpdateUserModel(emailUser,u_weight,u_height,u_name);

        new AsyncUpdateUser(this).execute(model);
    }
    @Override
    public void updateUser(String result) {
        if(result.equals("success")){
            Toast.makeText(getContext(),"Update with success",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(),"Problem with update",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void geUserByEmail(GetUserByEmailModel model) {
        if(model!=null){
            userModel = model;
            name.setText(userModel.getName());
            String weightGiven = String.valueOf(userModel.getWeight());
            weight.setText(weightGiven);

            String heightGiven = String.valueOf(userModel.getHeight());
            height.setText(heightGiven);

            byte[] decodeByte = Base64.decode(userModel.getProfilePicture(),Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);

            profilePicture.setImageBitmap(bitmap);
        }
    }
}
