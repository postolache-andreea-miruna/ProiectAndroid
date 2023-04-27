package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    @ColumnInfo(name = "year_born")
    private int yearBorn;

    @NonNull
    @ColumnInfo(name = "gender")
    private String gender;

    @NonNull
    @ColumnInfo(name = "height")
    private float height;

    @NonNull
    @ColumnInfo(name = "weight")
    private float weight;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(@NonNull String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @NonNull
    @ColumnInfo(name = "profilePicture")
    private String profilePicture;

    public User( @NonNull String email, @NonNull String password,@NonNull int yearBorn, @NonNull String gender, float height, float weight, @NonNull String name,@NonNull String profilePicture) {//int id
        //this.id = id;
        this.email = email;
        this.password = password;
        this.yearBorn = yearBorn;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    @NonNull
    public String getGender() {
        return gender;
    }

    public void setGender(@NonNull String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
