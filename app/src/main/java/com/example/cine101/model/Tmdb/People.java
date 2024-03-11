package com.example.cine101.model.Tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class People {

    private boolean adult;
    private double id;
    private String name;
    private String originalName;
    private String mediaType;
    private double popularity;
    private double gender;
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @SerializedName("profile_path")
    private String profilePath;
    private ArrayList<KnowFor> knownFor;
    public boolean isAdult() {
        return adult;
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getGender() {
        return gender;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public ArrayList<KnowFor> getKnownFor() {
        return knownFor;
    }
}
