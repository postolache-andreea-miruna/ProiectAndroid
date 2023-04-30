package com.example.proiectandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowAllGenderProgramsFragment extends Fragment implements ProgramOperation{
    RecyclerView rv;
    CustomAdapterProgram adapterProgram;
    public static List<ProgramModel> programsList = new ArrayList<>();

    public ShowAllGenderProgramsFragment(){
        super(R.layout.fragment_show_all_gender_programs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Fragment", "onViewCreated() called");
        new GetAllProgramsOperation(this).execute();

         adapterProgram = new CustomAdapterProgram(programsList);
        //folosim in recyclerView acest adapter
         rv = view.findViewById(R.id.program_recycler_view);
        rv.setAdapter(adapterProgram);

    }


    @Override
    public void getAllPrograms(List<ProgramModel> programs) {
        if(programs !=null){
            //programsList.addAll(programs);
            programsList.clear();
            programsList.addAll(programs);
            adapterProgram.notifyDataSetChanged();
        }
    }
}
