package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapterTrainingDetails extends RecyclerView.Adapter<CustomAdapterTrainingDetails.MyTrainingsViewHolder>{

    private List<ProgramDetails> trainings;

    public CustomAdapterTrainingDetails(List<ProgramDetails> trainings) {
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public CustomAdapterTrainingDetails.MyTrainingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_training_program,parent,false);
        return new CustomAdapterTrainingDetails.MyTrainingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterTrainingDetails.MyTrainingsViewHolder holder, int position) {
        holder.bind(trainings.get(position));
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }


    public static class MyTrainingsViewHolder extends RecyclerView.ViewHolder {
        private final TextView day_no;
        private final TextView day_name;
        private Button selectButton;

        public MyTrainingsViewHolder(@NonNull View itemView){
            super(itemView);

            day_no = itemView.findViewById(R.id.training_day_nr);
            day_name = itemView.findViewById(R.id.training_day_name);
            selectButton = itemView.findViewById(R.id.training_selectButton);

        }



        public void bind(ProgramDetails item){
            //setam datele in view
            String nr_of_day = String.valueOf(item.getId());
            day_no.setText(nr_of_day);

            day_name.setText(item.getName());

            selectButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                   //ceva
                    Context context = v.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putInt("ProgramulId",item.getIdProgram());
                    bundle.putInt("DayId",item.getId());
                    ExercicesForProgramDayFragment fragment = new ExercicesForProgramDayFragment();
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
