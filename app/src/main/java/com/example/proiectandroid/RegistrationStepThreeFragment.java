package com.example.proiectandroid;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class RegistrationStepThreeFragment extends Fragment implements UserOperations{
    public RegistrationStepThreeFragment(){
        super(R.layout.fragment_registration_step_three);
    }
    private static String NOTIFICATION_CHANEL_ID = "My channel";
    private AppCompatEditText editHeight;
    private AppCompatEditText editWeight;
    private Button register;

    private String email;
    private String password;
    private String name;
    private String image;

    private int yearBorn;
    private String gender;

    private String heightStr;
    private String weightStr;

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
                heightStr = "";
                weightStr="";
                if(editHeight.getText().toString() !=null &&
                editWeight.getText().toString()!=null){
                    heightStr = editHeight.getText().toString();
                    weightStr = editWeight.getText().toString();

                    if(heightStr.isEmpty() || weightStr.isEmpty()){
                        showErrorPopup();
                    }
                    else{
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

                        addNotification();

                        insertCurrentUser(height,weight);


                    }
                }
                else if(editHeight.getText()==null || editWeight.getText() == null){
                    showErrorPopup();
                }


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

    private void addNotification(){
        int id=1;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANEL_ID,"channel",importance);
            channel.setDescription("Login Here");
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());
//            NotificationManager notificationManager =requireActivity().getSystemService(NotificationManager.class );
            notificationManagerCompat.createNotificationChannel(channel);
        }
        Intent intent = new Intent(getContext(), MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getContext());
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),NOTIFICATION_CHANEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification")
                .setContentText("Welcome! Don't forget to login.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(id, builder.build());
    }

    @Override
    public void insertUser(String result) {
        if(result.equals("success")){
            Log.d("suuuccceees", "email: " + "daaaaaa");//null
            Toast.makeText(requireContext(),"User insert successfull",Toast.LENGTH_LONG).show();
            moveToLogin();
        }
        else{
            Log.d("insucces", "email: " + result);//null
            Toast.makeText(requireContext(),"Problem on insert user",Toast.LENGTH_LONG).show();
        }
    }
    private void moveToLogin () {

        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(R.anim.slide_in_right_custom, R.anim.slide_out_left_custom);

    }

    private void insertCurrentUser(float height,float weight){
        User user = new User(email, password, yearBorn, gender,height,weight,name,image);
        User[] users = new User[]{user};
        new InsertUserOperation(this).execute(users);
    }


    private void showErrorPopup(){
        new AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(R.string.error_register_F1)
                .setPositiveButton(android.R.string.yes, dismissPopup())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    private DialogInterface.OnClickListener dismissPopup(){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }


}
