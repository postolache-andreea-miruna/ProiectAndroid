package com.example.proiectandroid;

import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterStepOneFragment extends Fragment {

    public RegisterStepOneFragment(){
        super(R.layout.fragment_registration_step_one);
    }
    private TextView createAccountMessage;
    private ImageView profilePicture;
    private Button selectImageButton;

    private AppCompatEditText editEmail;
    private AppCompatEditText editPassword;
    private AppCompatEditText editName;
    private Bitmap selectedImage;

    private Button next;

    private String email;
    private String image;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        if (savedInstanceState != null) {
//            String emailul = savedInstanceState.getString("emailul");
//            editEmail.setText(emailul);
//
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         createAccountMessage = view.findViewById(R.id.register);
         profilePicture = view.findViewById(R.id.profileImageView);
         selectImageButton = view.findViewById(R.id.selectImageButton);
         editEmail = view.findViewById(R.id.emailEditText);
         editPassword = view.findViewById(R.id.passwordEditText);
         editName = view.findViewById(R.id.nameEditText);
         next = view.findViewById(R.id.nextButton);

        if (savedInstanceState != null) {
            String emailul = savedInstanceState.getString("emailul");
            editEmail.setText(emailul);

            editPassword.setText(savedInstanceState.getString("parola"));
            editName.setText(savedInstanceState.getString("numele"));
           // profilePicture.setImageBitmap(savedInstanceState.getParcelable("imaginea"));


           
//            if (imageData != null) {
//                selectedImage = BitmapFactory.decodeByteArray(imageData.getBytes(), 0, imageData.length());
//                profilePicture.setImageBitmap(selectedImage);
//            }


        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email ="";
                String password="";
                String name="";
                 image="";

                if(editEmail.getText()!=null &&
                    editPassword.getText()!=null &&
                    editName.getText()!=null &&
                selectedImage!=null)
                {
                    email = editEmail.getText().toString();
                    password = editPassword.getText().toString();
                    name = editName.getText().toString();


                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    selectedImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    byte[] byteArray = outputStream.toByteArray();
                    image = Base64.encodeToString(byteArray, Base64.DEFAULT);


                    if(email.isEmpty() || password.isEmpty() ||
                    name.isEmpty() || image.isEmpty()){
                        showErrorPopup();
                    }
                    else{
                        Bundle bundle = new Bundle();
                        bundle.putString("email", editEmail.getText().toString());
                        bundle.putString("password", editPassword.getText().toString());
                        bundle.putString("name", editName.getText().toString());
                        bundle.putString("image", image);

                        RegistrationStepTwoFragment fragmentTwo = new RegistrationStepTwoFragment();
                        fragmentTwo.setArguments(bundle);

                        // Call the onNextButtonClick() method on the callback interface
//                        if (getActivity() instanceof OnNextButtonClickListener) {
//                            ((OnNextButtonClickListener) getActivity()).onNextButtonClicked();
//                        }

                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragmentTwo);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
                else if(editEmail.getText()==null ||
                        editPassword.getText()==null ||
                        editName.getText()==null ||
                        selectedImage==null){
                    showErrorPopup();
                }


            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
//        if(savedInstanceState!=null){
//             email = savedInstanceState.getString("email");
//            editEmail.setText(email);
//      }
        Log.d("RegisterStepOneCreateView", "onSaveInstanceState() called with email: " + editEmail.getText().toString());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                profilePicture.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(!outState.isEmpty())
        {outState.putString("emailul", editEmail.getText().toString());
        outState.putString("numele", editName.getText().toString());
        outState.putString("parola", editPassword.getText().toString());}
    }


//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//       // Log.d("reg",savedInstanceState.getString("emailul"));
//        if (savedInstanceState != null) {
//
//            editName.setText(savedInstanceState.getString("numele"));
//            editPassword.setText(savedInstanceState.getString("parola"));
//             profilePicture.setImageBitmap(savedInstanceState.getParcelable("imaginea"));
//            //selectedImage.
//            //savedInstanceState.getString("imaginea");
//            String emailul = savedInstanceState.getString("emailul");
//            editEmail.setText(emailul);
//        }
//    }


}
