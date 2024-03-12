package com.example.cine101.responses;

import com.example.cine101.model.Tmdb.VideosTMDB;

import java.util.ArrayList;

public class VideosResponse {

    private long id;
    private ArrayList<VideosTMDB> results;


    public long getId() {
        return id;
    }

    public ArrayList<VideosTMDB> getResults() {
        return results;
    }
}
