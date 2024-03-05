package com.example.cine101.model;

import com.google.gson.annotations.SerializedName;

public class LastEpisodeToAir {
    private long id;
    private String name;
    private String overview;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private long voteCount;
    @SerializedName("air_date")
    private String airDate;
    @SerializedName("episode_number")
    private long episodeNumber;
    @SerializedName("episode_Type")
    private String episodeType;
    private String productionCode;
    private long runtime;
    @SerializedName("season_number")
    private long seasonNumber;
    @SerializedName("show_id")
    private long showID;
    @SerializedName("still_path")
    private String stillPath;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }


    public long getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeType() {
        return episodeType;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public long getRuntime() {
        return runtime;
    }

    public long getSeasonNumber() {
        return seasonNumber;
    }

    public long getShowID() {
        return showID;
    }

    public String getStillPath() {
        return stillPath;
    }
}
