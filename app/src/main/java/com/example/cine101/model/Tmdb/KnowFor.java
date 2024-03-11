package com.example.cine101.model.Tmdb;

import java.util.ArrayList;

public class KnowFor {
    private boolean adult;
    private String backdropPath;
    private double id;
    private String title;
    private String  originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String mediaType;
    private ArrayList<Genre> genreIDS;
    private double popularity;
    private String releaseDate;
    private Boolean video;
    private double voteAverage;
    private double voteCount;
    private String name;
    private String originalName;
    private String firstAirDate;
    private String[] originCountry;


    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public ArrayList<Genre> getGenreIDS() {
        return genreIDS;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Boolean getVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }
}
