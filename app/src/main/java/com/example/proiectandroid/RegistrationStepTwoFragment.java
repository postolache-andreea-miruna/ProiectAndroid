package com.example.proiectandroid;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class RegistrationStepTwoFragment extends Fragment {
    public RegistrationStepTwoFragment(){
        super(R.layout.fragment_registration_step_two);
    }
    private AppCompatEditText editYearBorn;
    private Spinner genderSpinner;
    private Button nextButton;

    private String email;
    private String password;
    private String name;
    private String image;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            email = getArguments().getString("email");
            password = getArguments().getString("password");
            name = getArguments().getString("name");
            image = getArguments().getString("image");

            Log.d("email", "emailF2: " + email);
            Log.d("password", " passwordF2: " + password);
            Log.d("name", "nameF2: " + name);
            Log.d("image", "imageF2: " + image);



        editYearBorn = view.findViewById(R.id.yearBornEditText);
        genderSpinner = view.findViewById(R.id.genderSpinner);
        nextButton = view.findViewById(R.id.nextButton);


        String[] genders = {"Female", "Male"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("yearBorn", editYearBorn.getText().toString());
                bundle.putString("gender", genderSpinner.getSelectedItem().toString());
                bundle.putString("email", email);
                bundle.putString("password", password);
                bundle.putString("name", name);
                bundle.putString("image", image);

                RegistrationStepThreeFragment fragmentThree = new RegistrationStepThreeFragment();
                fragmentThree.setArguments(bundle);

                // Call the onNextButtonClick() method on the callback interface
//                if (getActivity() instanceof OnNextButtonClickListener) {
//                    ((OnNextButtonClickListener) getActivity()).onNextButtonClickedFromF2();
//                }
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentThree);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
}

}
