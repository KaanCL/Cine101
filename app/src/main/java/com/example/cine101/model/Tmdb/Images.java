package com.example.cine101.model.Tmdb;
import com.google.gson.annotations.SerializedName;
public class Images {

    private double aspect_ratio;
    private long height;
    private String file_path;
    private double vote_average;
    private long vote_count;
    private long width;

    public double getAspect_ratio() {
        return aspect_ratio;
    }

    public long getHeight() {
        return height;
    }

    public String getFile_path() {
        return file_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public long getVote_count() {
        return vote_count;
    }

    public long getWidth() {
        return width;
    }
}
