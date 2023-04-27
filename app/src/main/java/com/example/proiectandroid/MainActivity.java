package com.example.proiectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity {

    private TextView mesaj;
    private Button registerButton;

    private Button buttonToRegistration;
    private AppCompatEditText editEmail;
    private AppCompatEditText editPass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//imi ia layoultul

        //referentiem elementele din front
        mesaj = findViewById(R.id.bunVenit);
        registerButton = findViewById(R.id.button);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        buttonToRegistration = findViewById(R.id.buttonToRegitration);

        //cand apasam pe buton se deschide o noua activitate care afiseaza pe ecran ceea ce am introdus pe input

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ce se intampla pe click
                String email = "";
                String passw = "";
                if(editEmail.getText()!= null && editPass.getText()!=null){
                    email = editEmail.getText().toString();
                    passw = editPass.getText().toString();

                    if(email.isEmpty() || passw.isEmpty()){
                        showErrorPopup();
                    }
                    else{
                       /* mesaj.setText(getString(R.string.bun_venit) + " "+ email);*/
                        goToSecActiv(email);
                    }
                }
            }
        });


        buttonToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();}});

    }


    private void goToRegister(){//sa mergem pe act urm cand apas pe buton
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
    private void goToSecActiv(String extra){//sa mergem pe act urm cand apas pe buton
        Intent intent = new Intent(this,SecondActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("MESSAGE",extra);


        //intent.putExtra("MESSAGE",extra);
        intent.putExtras(bundle);
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
}
