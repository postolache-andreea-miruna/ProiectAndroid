package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapterMyPrograms extends RecyclerView.Adapter<CustomAdapterMyPrograms.MyProgramsViewHolder>{

    private List<MyProgramsModel> programs;
    public CustomAdapterMyPrograms(List<MyProgramsModel> programs){
        this.programs = programs;
    }

    @NonNull
    @Override
    public CustomAdapterMyPrograms.MyProgramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myprograms,parent,false);
        return new CustomAdapterMyPrograms.MyProgramsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterMyPrograms.MyProgramsViewHolder holder, int position) {
        holder.bind(programs.get(position));
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }


    public static class MyProgramsViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView no_days;
        private final TextView programPlace;
        private final TextView startDate;
        private final TextView finishDate;

        private Button selectButton;

        private int userId;
        private Choose choose;

        //definim structura unui program
        public MyProgramsViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.my_program_name);
            no_days = itemView.findViewById(R.id.my_no_of_days);
            programPlace = itemView.findViewById(R.id.my_program_outside);

            startDate = itemView.findViewById(R.id.my_start_date);
            finishDate = itemView.findViewById(R.id.my_finish_date);

            selectButton = itemView.findViewById(R.id.myselectButton);

        }


        public void bind(MyProgramsModel item){
            //setam datele in view
            name.setText(item.getName());
            String nr_days = String.valueOf(item.getNo_days());
            no_days.setText(nr_days);

            if(item.isOutdoor())
            {
                programPlace.setText("outside");
            }else{
                programPlace.setText("inside");
            }


            if(item.getDateFinish()==null){
                finishDate.setText("");
            }
            else {
                Date date = item.getDateFinish();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate = formatter.format(date);
                finishDate.setText(strDate);
            }

            Date date = item.getDateStart();
            SimpleDateFormat formatterSt = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatterSt.format(date);
            startDate.setText(strDate);


            selectButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //cand dam click vrem sa se insereze in choose
                    Context context = v.getContext();
                    SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
                    String emailUser = preferences.getString(PREFERENCES_ID_KEY,"");
                    //cand dau cllick vreau sa ma duc in alta activitate dar cu niste date deja luate
                }
            });

        }
    }
}
