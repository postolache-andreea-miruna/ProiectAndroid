package com.example.proiectandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapterTrainingExercises extends RecyclerView.Adapter<CustomAdapterTrainingExercises.MyTrainingsExViewHolder>{
    private List<ProgramExercise> trainings;

    public CustomAdapterTrainingExercises(List<ProgramExercise> trainings) {
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public MyTrainingsExViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programs_exercise,parent,false);
        return new CustomAdapterTrainingExercises.MyTrainingsExViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTrainingsExViewHolder holder, int position) {
        holder.bind(trainings.get(position));
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }


    public static class MyTrainingsExViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView description;
        private Button selectButton;
        public MyTrainingsExViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.exercise_name);
            description = itemView.findViewById(R.id.exercise_description);
            selectButton = itemView.findViewById(R.id.exercise_button);
        }

        public void bind(ProgramExercise item){
            //setam datele in view

            name.setText(item.getName());
            description.setText(item.getDetails());

            selectButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //ceva
                    Context context = v.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ExerciseId",item.getIdExercise());
                    DetailsForGivenExerciseFragment fragment = new DetailsForGivenExerciseFragment();
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = ((SecondActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_containeruul, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });



        }
    }
}
