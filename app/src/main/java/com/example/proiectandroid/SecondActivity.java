package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;
import static com.example.proiectandroid.ShowAllGenderProgramsFragment.programsList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
  //  private VideoView videoView;
    private MediaController mediaController;
    private LinearLayout videoLayout;


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signout;

    private String emailUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //facem legatura cu layout
        setContentView(R.layout.activity_second);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            ShowAllGenderProgramsFragment fragment = new ShowAllGenderProgramsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_containeruul, ShowAllGenderProgramsFragment.class,null)
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);


        }





//        videoView = findViewById(R.id.video_view);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/my_video");
//        videoView.setVideoURI(uri);
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//
//        videoView.start();



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

        Context context = getApplicationContext();
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        emailUser = preferences.getString(PREFERENCES_ID_KEY,"");
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
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_containeruul, new AboutFragment())
                    .commit();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_home) {
            ShowAllGenderProgramsFragment fragment = new ShowAllGenderProgramsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_containeruul, fragment)
                    .commit();
            // Access the fragment's methods or properties here
          //  fragment.getAllPrograms(programsList);

        }
        else if(id == R.id.nav_my_programs){
            MyProgramsFragment fragment = new MyProgramsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_containeruul, fragment)
                    .commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.nav_about:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_containeruul, new AboutFragment()).commit();
//                        break;
//            case R.id.nav_logout:
//                Toast.makeText(this,"Logout!",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_home:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_containeruul, new ShowAllGenderProgramsFragment()).commit();
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return  true;
//    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
