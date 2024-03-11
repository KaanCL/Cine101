package com.example.cine101.model.Tmdb;

import java.util.ArrayList;

public class Episodes {

    private String air_date ;
    private double episode_number ;
    private String episode_type;
    private double id ;
    private String name ;
    private String overview;
    private String production_code;
    private double runtime;
    private double season_number;
    private double show_id ;
    private String still_path;
    private double vote_average;
    private double vote_count;
    private ArrayList<Cast> crew;
    private ArrayList<Cast> gues_stars;


    public String getAir_date() {
        return air_date;
    }

    public double getEpisode_number() {
        return episode_number;
    }

    public String getEpisode_type() {
        return episode_type;
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getProduction_code() {
        return production_code;
    }

    public double getRuntime() {
        return runtime;
    }

    public double getSeason_number() {
        return season_number;
    }

    public double getShow_id() {
        return show_id;
    }

    public String getStill_path() {
        return still_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public double getVote_count() {
        return vote_count;
    }

    public ArrayList<Cast> getCrew() {
        return crew;
    }

    public ArrayList<Cast> getGues_stars() {
        return gues_stars;
    }
}
