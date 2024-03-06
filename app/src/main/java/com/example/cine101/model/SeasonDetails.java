package com.example.cine101.model;

import java.util.ArrayList;

public class SeasonDetails {

    private String _id ;
    private String air_date ;
    private ArrayList<Episodes> episodes;
    private String name;
    private String overview;
    private double id;
    private String poster_path;
    private double season_number;
    private double vote_average;

    public String get_id() {
        return _id;
    }

    public String getAir_date() {
        return air_date;
    }

    public ArrayList<Episodes> getEpisodes() {
        return episodes;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public double getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public double getSeason_number() {
        return season_number;
    }

    public double getVote_average() {
        return vote_average;
    }
}
