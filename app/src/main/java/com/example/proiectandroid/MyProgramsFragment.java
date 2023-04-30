package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyProgramsFragment extends Fragment implements MyProgramOperation{
    RecyclerView rv;
    private String emailUser;
    CustomAdapterMyPrograms adapterMyPrograms;
    public static List<MyProgramsModel> programsList = new ArrayList<>();

    public MyProgramsFragment(){
        super(R.layout.fragments_show_my_programs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        emailUser = preferences.getString(PREFERENCES_ID_KEY,"");

        new GetAllMyProgramsOperation(this).execute(emailUser);

        adapterMyPrograms = new CustomAdapterMyPrograms(programsList);
        //folosim in recyclerView acest adapter
        rv = view.findViewById(R.id.my_program_recycler_view);
        rv.setAdapter(adapterMyPrograms);

    }


    @Override
    public void getAllMyPrograms(List<MyProgramsModel> programs) {
        if(programs !=null){
            //programsList.addAll(programs);
            programsList.clear();
            programsList.addAll(programs);
            adapterMyPrograms.notifyDataSetChanged();
        }
    }
}
