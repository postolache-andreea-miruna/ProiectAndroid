package com.example.proiectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements  UserOperationLogin,UserOperationLoginGoogle{
    private BroadCastReceiverPlane airplaneModeChangeReceiver;


/*
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneModeChangeReceiver);
    }

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleButton;
    public final static String PREFERENCES_KEY = "preferences key";
    public final static String PREFERENCES_ID_KEY = "preferences key id";
    private TextView mesaj;
    private Button loginButton;

    private Button buttonToRegistration;
    private AppCompatEditText editEmail;
    private AppCompatEditText editPass;

    private String emailUser;

    private String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//imi ia layoultul


        airplaneModeChangeReceiver = new BroadCastReceiverPlane();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            email = account.getEmail();
            verifyIfUserHasAccountGoogle(email);
            //navigateToRegisterGoogle();
        }

        googleButton = findViewById(R.id.googleBtn);
        //referentiem elementele din front
        mesaj = findViewById(R.id.bunVenit);
        loginButton = findViewById(R.id.button);//buton de login
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        buttonToRegistration = findViewById(R.id.buttonToRegitration);

        googleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                signInGoogle();
            }
        });

        //cand apasam pe buton se deschide o noua activitate care afiseaza pe ecran ceea ce am introdus pe input

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ce se intampla pe click
                 emailUser = "";
                String passw = "";
                if(editEmail.getText()!= null && editPass.getText()!=null){
                    emailUser = editEmail.getText().toString();
                    passw = editPass.getText().toString();




                    if(emailUser.isEmpty() || passw.isEmpty()){
                        showErrorPopup();
                    }
                    else{
                       /* mesaj.setText(getString(R.string.bun_venit) + " "+ email);*/
//salvam in share pref email
                        verifyIfUserHasAccount(emailUser,passw);


                    }
                }
            }
        });


        buttonToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();}});

    }


    void signInGoogle(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                if(account!=null){
                    email = account.getEmail();
                    verifyIfUserHasAccountGoogle(email);
                    //navigateToRegisterGoogle();
                }

               // navigateToRegisterGoogle();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"Sorry! Something went wrong.",Toast.LENGTH_LONG).show();
            }
        }
    }
    private void verifyIfUserHasAccountGoogle(String email){

        new FindLoginGoogleOperation(this).execute(email);
    }
    @Override
    public void userHasGoogleAccount(Integer result) {
        if(result == 1){
            makePreferences(email);
            goToSecActiv();
        }
        else{
            navigateToRegisterGoogle();
            Toast.makeText(this,"First time here",Toast.LENGTH_LONG).show();
        }
    }


    void navigateToRegisterGoogle(){
        finish();
        Intent intent = new Intent(this,GoogleRegistrationActivity.class);
        startActivity(intent);
    }

    private void goToRegister(){//sa mergem pe act urm cand apas pe buton
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
    private void goToSecActiv(){//sa mergem pe act urm cand apas pe buton
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    private void showErrorPopup(){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_email)
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

    private void verifyIfUserHasAccount(String email, String passw){
        EmailPassw emailPassw = new EmailPassw();
        emailPassw.email = email;
        emailPassw.passw = passw;

        new FindLoginUserOperation(this).execute(emailPassw);
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


    @Override
    public void userHasAccount(Integer result) {
        if(result == 1){
            makePreferences(emailUser);
            goToSecActiv();
        }
        else{
            Toast.makeText(this,"Password or Email are incorrect",Toast.LENGTH_LONG).show();
        }
    }
}
