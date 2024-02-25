package com.example.cine101.model;

import com.google.gson.annotations.SerializedName;

public class Serie {
    private boolean adult;
    private String backdropPath;
    private double[] genreIDS;
    private double id;
    private String[] originCountry;
    private String originalLanguage;
    private String originalName;
    private String overview;
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;
    private String firstAirDate;
    private String name;
    @SerializedName("vote_average")
    private double voteAverage;
    private long voteCount;

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double[] getGenreIDS() {
        return genreIDS;
    }

    public double getId() {
        return id;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getName() {
        return name;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
