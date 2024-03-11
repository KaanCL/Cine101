package com.example.cine101.model.Tmdb;

import com.google.gson.annotations.SerializedName;

public class CreatedBy {
    private long id;
    @SerializedName("credit_id")
    private String creditID;
    private String name;
    private long gender;
    @SerializedName("profile_path")
    private String profilePath;

    public long getId() {
        return id;
    }

    public String getCreditID() {
        return creditID;
    }

    public String getName() {
        return name;
    }

    public long getGender() {
        return gender;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
