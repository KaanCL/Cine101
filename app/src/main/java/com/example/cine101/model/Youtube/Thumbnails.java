package com.example.cine101.model.Youtube;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Thumbnails {

    @SerializedName("default")
    private Thumbnaildefault thumbnaildefault;

    @SerializedName("medium")
    private Thumbnailmediums medium;

    @SerializedName("standard")
    private Thumbnailstandart standart;

    public Thumbnailmediums getMedium() {
        return medium;
    }

    public Thumbnaildefault getThumbnaildefault() {
        return thumbnaildefault;
    }

    public Thumbnailstandart getStandart() {
        return standart;
    }
}

