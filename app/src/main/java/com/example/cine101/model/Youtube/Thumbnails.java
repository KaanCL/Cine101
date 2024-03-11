package com.example.cine101.model.Youtube;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {
    @SerializedName("default")
   private Default thumbnaildefaults;

    @SerializedName("medium")
   private Default thumbnailmediums;

    @SerializedName("high")
   private Default thumbnailhigh;

  @SerializedName("standart")
   private Default thumbnailstandart;

  @SerializedName("maxres")
   private Default thumbnailmaxres;


    public Default getThumbnaildefaults() {
        return thumbnaildefaults;
    }

    public Default getThumbnailmediums() {
        return thumbnailmediums;
    }

    public Default getThumbnailhigh() {
        return thumbnailhigh;
    }

    public Default getThumbnailstandart() {
        return thumbnailstandart;
    }

    public Default getThumbnailmaxres() {
        return thumbnailmaxres;
    }
}
