package com.example.cine101.model.Tmdb;

import com.google.gson.annotations.SerializedName;

public class VideosTMDB {

    @SerializedName("iso_639_1")
    private String iso_639_1 ;

    @SerializedName("iso_3166_1")
    private String iso_3166_1 ;

    private String name;
    private String key;
    private String site;
    private float size;
    private String type;
    private String official;
    private String published_at;
    private String id;


    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public float getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getOfficial() {
        return official;
    }

    public String getPublished_at() {
        return published_at;
    }

    public String getId() {
        return id;
    }
}
