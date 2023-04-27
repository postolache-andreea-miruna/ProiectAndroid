package com.example.proiectandroid;

public class UserCreateModel {
    private String email;
    private String password;
    private int yearBorn;
    private String gender;
    private float height;
    private float weight;
    private String name;

    public UserCreateModel(String email, String password, int yearBorn, String gender, float height, float weight, String name) {
        this.email = email;
        this.password = password;
        this.yearBorn = yearBorn;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
