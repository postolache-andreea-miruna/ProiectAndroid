package com.example.proiectandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsForGivenExerciseFragment extends Fragment implements GetDetailsExerciseOperation{
    public DetailsForGivenExerciseFragment(){
        super(R.layout.fragment_detail_for_given_exercise);
    }
    private TextView details_exercise;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        details_exercise = view.findViewById(R.id.detalii_exercitiu);
        Bundle bundle = getArguments();
        int exerciseId = bundle.getInt("ExerciseId");
        getDetail(exerciseId);
    }

    private void getDetail(int idExercise){
        new AsyncTaskDetailExercise(this).execute(idExercise);
    }

    @Override
    public void getDetailExercise(String detail) {
        if(detail!=null){
            details_exercise.setText(detail);
        }
    }
}
