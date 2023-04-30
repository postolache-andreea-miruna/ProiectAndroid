package com.example.proiectandroid;

public class UpdateUserModel {
    private String email;
    private float userWeight;
    private float userHeight;
    private String name;

    public UpdateUserModel(String email, float userWeight, float userHeight,String name) {
        this.email = email;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(float userWeight) {
        this.userWeight = userWeight;
    }

    public float getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(float userHeight) {
        this.userHeight = userHeight;
    }
}
