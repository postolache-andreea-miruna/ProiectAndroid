package com.example.proiectandroid;

import static com.example.proiectandroid.MainActivity.PREFERENCES_ID_KEY;
import static com.example.proiectandroid.MainActivity.PREFERENCES_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomAdapterProgram extends RecyclerView.Adapter<CustomAdapterProgram.ProgramViewHolder> {

    private List<ProgramModel> programs;

    public CustomAdapterProgram(List<ProgramModel> programs){
        this.programs = programs;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program,parent,false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        holder.bind(programs.get(position));
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }


    public static class ProgramViewHolder extends RecyclerView.ViewHolder implements FindIdForEmailOperation,ChooseOperationInsert{

        private final TextView name;
        private final TextView no_days;
        private final TextView programPlace;

        private Button selectButton;

        private int userId;
        private Choose choose;

        //definim structura unui program
        public ProgramViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.program_name);
            no_days = itemView.findViewById(R.id.no_of_days);
            programPlace = itemView.findViewById(R.id.program_outside);

            selectButton = itemView.findViewById(R.id.selectButton);

        }


        public void bind(ProgramModel item){
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

            selectButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //cand dam click vrem sa se insereze in choose
                    Context context = v.getContext();
                    SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
                    String emailUser = preferences.getString(PREFERENCES_ID_KEY,"");
                    choose(emailUser, item.getId());
                }
            });

        }

/*
  private void insertCurrentUser(float height,float weight){
        User user = new User(email, password, yearBorn, gender,height,weight,name,image);
        User[] users = new User[]{user};
        new InsertUserOperation(this).execute(users);
    }

* */
       public void choose(String emailUser, int programId) {

           Date date = new Date();
           choose = new Choose(0,programId,date,null);

            new AsyncTaskIdFromEmail(this).execute(emailUser);

        }

        @Override
        public void idForEmail(Integer id) {
            //returns the email
            if(id!=null)
            {userId = id;
                choose.setIdUser(id);
                Choose[] chooses = new Choose[]{choose};

                new AsyncTaskChooseInsert(this).execute(chooses);}
        }

        @Override
        public void insertChoose(String result) {
            if(result.equals("success")){
                Toast.makeText(selectButton.getContext(), "Selected successfully",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(selectButton.getContext(), "Problem with selection",Toast.LENGTH_LONG).show();
            }
        }
    }
}
