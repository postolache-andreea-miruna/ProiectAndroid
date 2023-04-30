package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrainingForProgramIdFragment extends Fragment implements TrainingGetAllByIdProgram{
    RecyclerView rv;
    CustomAdapterTrainingDetails customAdapterTrainingDetails;
    public static List<ProgramDetails> trainingList = new ArrayList<>();
    public TrainingForProgramIdFragment(){
        super(R.layout.fragment_training_for_program_id);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        int programId = bundle.getInt("ProgramId");

        new AsyncTaskTrainingGetByIdPr(this).execute(programId);

        customAdapterTrainingDetails = new CustomAdapterTrainingDetails(trainingList);
        //folosim in recyclerView acest adapter
        rv = view.findViewById(R.id.training_program_recycler_view);
        rv.setAdapter(customAdapterTrainingDetails);

    }

    @Override
    public void getAllDaysIdProgram(List<ProgramDetails> details) {
        if (details != null) {
            //programsList.addAll(programs);
            trainingList.clear();
            trainingList.addAll(details);
            customAdapterTrainingDetails.notifyDataSetChanged();
        }
    }
}
