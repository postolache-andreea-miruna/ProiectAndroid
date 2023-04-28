package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //facem legatura cu layout
        setContentView(R.layout.activity_second);
//        Intent intent = getIntent();
//
//        Bundle bundle = intent.getExtras();
//
//        //String message = intent.getStringExtra("MESSAGE");
//        String message = bundle.getString("MESSAGE");
//
        signout = findViewById(R.id.signout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(SecondActivity.this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        String emailUser = preferences.getString(PREFERENCES_ID_KEY,"");
        int atIndex = emailUser.indexOf('@');
        String username = "";

        if (atIndex != -1) { // Check if "@" symbol is present in the email
            username = emailUser.substring(0, atIndex); // Extract the substring before the "@"
        }
        TextView text = findViewById(R.id.mesaj2);
        text.setText(getString(R.string.hi) + " "+ username);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }

}
