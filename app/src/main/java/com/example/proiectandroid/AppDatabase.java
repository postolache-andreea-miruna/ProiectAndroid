package com.example.proiectandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Program.class, Choose.class, Exercise.class, Day.class, Training.class}, version = 3)
//la entities punem toate tabelele din baza
//la versiune: daca dupa ce rulam mai modificam ceva la baza atunci incrementam versiunea
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ProgramDao programDao();
    public abstract ChooseDao chooseDao();
    public abstract ExerciseDao exerciseDao();
    public abstract DayDao dayDao();
}
