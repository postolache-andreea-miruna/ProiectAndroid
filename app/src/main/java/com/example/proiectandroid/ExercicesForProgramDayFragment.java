package com.example.proiectandroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExercicesForProgramDayFragment extends Fragment implements TrainingExerciseByIdProgram{
    RecyclerView rv;

    CustomAdapterTrainingExercises customAdapterTrainingExercises;
    public static List<ProgramExercise> trainingList = new ArrayList<>();

    public ExercicesForProgramDayFragment(){
        super(R.layout.fragment_training_exercises_for_program);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        int programId = bundle.getInt("ProgramulId");
        int dayId = bundle.getInt("DayId");
        ModelExerciseTraining model = new ModelExerciseTraining();
        model.dayId = dayId;
        model.programId = programId;
        new AsyncTaskTrainingExercise(this).execute(model);

        customAdapterTrainingExercises = new CustomAdapterTrainingExercises(trainingList);
        //folosim in recyclerView acest adapter
        rv = view.findViewById(R.id.training_ex_program_recycler_view);
        rv.setAdapter(customAdapterTrainingExercises);

    }

    @Override
    public void getAllExercisesIdProgramDay(List<ProgramExercise> exercises) {
        if (exercises != null) {
            //programsList.addAll(programs);
            trainingList.clear();
            trainingList.addAll(exercises);
            customAdapterTrainingExercises.notifyDataSetChanged();
        }
    }
}
