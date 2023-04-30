package com.example.proiectandroid;

public class GetUserByEmailModel {
    private String name;
    private String profilePicture;
    private float height;
    private float weight;

    public GetUserByEmailModel(String name, String profilePicture, float height, float weight) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
}
