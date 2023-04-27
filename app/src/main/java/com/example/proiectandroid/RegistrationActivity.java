package com.example.proiectandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class RegistrationActivity extends AppCompatActivity implements
        OnNextButtonClickListener{
    private  static  final  int FRAGMENT_STEP_ONE = 1;
    //private ViewPager viewPager; // ViewPager for handling fragment swiping
   // private RegistrationPagerAdapter pagerAdapter; // Adapter for ViewPager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_container, RegisterStepOneFragment.class,null)
                .commit();
    }
   @Override
    public void onNextButtonClicked() {
        RegistrationStepTwoFragment fragmentTwo = new RegistrationStepTwoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentTwo)
                .commit();
    }

    @Override
    public void onNextButtonClickedFromF2() {
        RegistrationStepThreeFragment fragmentThree = new RegistrationStepThreeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentThree)
                .commit();
    }

}

