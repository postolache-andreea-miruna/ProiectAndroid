package com.example.proiectandroid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

public class RegistrationStepThreeFragment extends Fragment implements UserOperations{
    public RegistrationStepThreeFragment(){
        super(R.layout.fragment_registration_step_three);
    }
    private AppCompatEditText editHeight;
    private AppCompatEditText editWeight;
    private Button register;

    private String email;
    private String password;
    private String name;
    private String image;

    private int yearBorn;
    private String gender;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            email = getArguments().getString("email");
            password = getArguments().getString("password");
            name = getArguments().getString("name");
            image = getArguments().getString("image");
            yearBorn = Integer.parseInt(getArguments().getString("yearBorn"));
            gender = getArguments().getString("gender");
        }

        editHeight = view.findViewById(R.id.heightEditText);
        editWeight = view.findViewById(R.id.weightEditText);
        register = view.findViewById(R.id.registerButton);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               float height = Float.parseFloat(editHeight.getText().toString());
               float weight = Float.parseFloat(editWeight.getText().toString());
                Log.d("email", "email: " + email);
                Log.d("password", " password: " + password);
                Log.d("yearBorn", "year: " + yearBorn);
                Log.d("gender", "gender: " + gender);
                Log.d("height", "height: " + height);
                Log.d("weight", "weight: " + weight);
                Log.d("name", "name: " + name);
                Log.d("image", "image: " + image);

                insertCurrentUser(height,weight);
               // User user = new User(email, password, yearBorn, gender,height,weight,name,image);


               // RegistrationStepThreeFragment fragment = RegistrationStepThreeFragment.this;
               // new InsertUserOperation(RegistrationStepThreeFragment.this).execute(user);
                // Call the onNextButtonClick() method on the callback interface
//                if (getActivity() instanceof OnNextButtonClickListener) {
//                    ((OnNextButtonClickListener) getActivity()).onNextButtonClickedFromF2();
//                }
            }
        });
    }

    @Override
    public void insertUser(String result) {
        if(result.equals("success")){
            Log.d("suuuccceees", "email: " + "daaaaaa");//null
            Toast.makeText(requireContext(),"User insert successfull",Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("suuuccceees", "email: " + result);//null
            Toast.makeText(requireContext(),"Problem on insert user",Toast.LENGTH_LONG).show();
        }
    }

    private void insertCurrentUser(float height,float weight){
        User user = new User(email, password, yearBorn, gender,height,weight,name,image);
        User[] users = new User[]{user};
        new InsertUserOperation(this).execute(users);
    }
}
