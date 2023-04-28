package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;

public class GoogleRegistrationActivity extends AppCompatActivity implements UserOperations{//,UserOperationLoginGoogle{
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    String email;
    String name;

    String urlImageGoogle;
    String base64Image;

    TextView entrenceMessage;
    AppCompatEditText password;
    AppCompatEditText year;
    Spinner gender;
    AppCompatEditText height;
    AppCompatEditText weight;
    Button complete;//cand salvam si userul in bd
   // Button signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_registration);

        password = findViewById(R.id.passwordEditTextGoogle);
        year = findViewById(R.id.yearBornEditTextul);
        gender = findViewById(R.id.genderSpinnerul);
        height = findViewById(R.id.heightEditTextul);
        weight = findViewById(R.id.weightEditTextul);

        complete = findViewById(R.id.completeButton);
        entrenceMessage = findViewById(R.id.completeRegGoogle);

      //  signout = findViewById(R.id.signout);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        String[] genders = {"Female", "Male"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            name = account.getDisplayName();
            email = account.getEmail();

           // verifyIfUserHasAccount(email);
            //DACA EXISTA EMAILUL IN BAZA DE DATE ATUNCI FAC UN REDIRECT IN ALTA PAGINA SI SETEZ SI PREFERENCES





            urlImageGoogle = String.valueOf(account.getPhotoUrl());

            Picasso.get()
                    .load(urlImageGoogle)
                    .into(new Target() {
                              @Override
                              public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                  bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                  byte[] byteArray = outputStream.toByteArray();
                                  base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                              }

                              @Override
                              public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                  Toast.makeText(GoogleRegistrationActivity.this, "Failed to load profile picture.", Toast.LENGTH_LONG).show();
                              }

                              @Override
                              public void onPrepareLoad(Drawable placeHolderDrawable) {
                                  Toast.makeText(GoogleRegistrationActivity.this, "Image is loading.", Toast.LENGTH_LONG).show();
                              }
                          });
             }


       complete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String passwText = "";
               String yearText = "";
               String genderText = "";
               String heightText ="";
               String weightText = "";

               if(password.getText()!=null
               && year.getText()!=null
               && gender.getSelectedItem()!=null
               && height.getText()!=null
               && weight.getText()!=null){

                   passwText = password.getText().toString();
                   yearText = year.getText().toString();
                   genderText = gender.getSelectedItem().toString();
                   heightText = height.getText().toString();
                   weightText = weight.getText().toString();

                   if(passwText.isEmpty() || yearText.isEmpty() ||
                           genderText.isEmpty()|| heightText.isEmpty() ||
                           weightText.isEmpty() || base64Image.isEmpty())
                   {
                       showErrorPopup();
                   }
                   else
                   {
                       int yearBorn = Integer.parseInt(yearText);
                       float height = Float.parseFloat(heightText);
                       float weight = Float.parseFloat(weightText);

                       insertCurrentUser(email,passwText,yearBorn,genderText,height,weight,name,base64Image);
                   }
               }
               else if(password.getText()==null
                       || year.getText()==null
                       || gender.getSelectedItem()==null
                       || height.getText()==null
                       || weight.getText()==null) {
                   showErrorPopup();
               }
           }
       });

//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signOut();
//            }
//        });
    }

//    void signOut(){
//        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(Task<Void> task) {
//                finish();
//                startActivity(new Intent(GoogleRegistrationActivity.this,MainActivity.class));
//            }
//        });
//    }

    private void insertCurrentUser(String email,String password, int yearBorn, String gender, float height, float weight,  String name, String image){
        User user = new User(email, password, yearBorn, gender,height,weight,name,image);
        User[] users = new User[]{user};
        new InsertUserOperation(this).execute(users);
    }
    @Override
    public void insertUser(String result) {
        if(result.equals("success")){
            Toast.makeText(this,"User insert successfull",Toast.LENGTH_LONG).show();
            makePreferences(email);
            goToSecActiv();
        }
        else{
            Log.d("insucces", "email: " + result);//null
            Toast.makeText(this,"Problem on insert user",Toast.LENGTH_LONG).show();
        }
    }
    private void goToSecActiv(){//sa mergem pe act urm cand apas pe buton
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    private void makePreferences(String emailUser){
        Context context = getApplicationContext();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();//incepem o sesiune

        editor.putString(PREFERENCES_ID_KEY,emailUser);
        editor.apply();//pentru salvare

        String emailRead = preferences.getString(PREFERENCES_ID_KEY,"");//valoarea default
        Toast.makeText(context,emailRead,Toast.LENGTH_LONG).show();
    }
//    private void verifyIfUserHasAccount(String email){
//
//        new FindLoginGoogleOperation(this).execute(email);
//    }
//    @Override
//    public void userHasGoogleAccount(Integer result) {
//        if(result == 1){
//            makePreferences(email);
//            goToSecActiv();
//        }
//        else{
//            Toast.makeText(this,"First time here",Toast.LENGTH_LONG).show();
//        }
//    }
    private void showErrorPopup(){
        new AlertDialog.Builder(this)
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
