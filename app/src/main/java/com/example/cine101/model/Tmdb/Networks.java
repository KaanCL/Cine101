package com.example.cine101.model.Tmdb;

import com.google.gson.annotations.SerializedName;

public class Networks {
    private long id;
    @SerializedName("logo_path")
    private String logoPath;
    private String name;
    @SerializedName("origin_country")
    private String originCountry;


    public long getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }
}
