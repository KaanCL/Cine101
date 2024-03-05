package com.example.cine101.model;

import com.google.gson.annotations.SerializedName;

public class Season {
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_count")
    private long episodeCount;
    private long id;
    private String name;
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("season_number")
    private long seasonNumber;
    @SerializedName("vote_average")
    private double voteAverage;


    public String getAirDate() {
        return airDate;
    }

    public long getEpisodeCount() {
        return episodeCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public long getSeasonNumber() {
        return seasonNumber;
    }

    public double getVoteAverage() {
        return voteAverage;
    }
}
