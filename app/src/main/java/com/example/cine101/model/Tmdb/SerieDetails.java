package com.example.cine101.model.Tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SerieDetails {
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("created_by")
    private ArrayList<CreatedBy> createdBy;
    @SerializedName("episode_run_time")
    private Object[] episodeRunTime;
    @SerializedName("first_air_date")
    private String firstAirDate;
    private ArrayList<Genre> genres;
    private String homepage;
    private long id;
    @SerializedName("in_production")
    private boolean inProduction;
    private String[] languages;
    @SerializedName("last_air_date")
    private String lastAirDate;
    private ArrayList<LastEpisodeToAir> lastEpisodeToAir;
    private String name;
    @SerializedName("next_episode_to_air")
    private Object nextEpisodeToAir;
    private ArrayList<Networks> networks;
    @SerializedName("number_of_episodes")
    private double numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private double numberOfSeasons;
    private String[] originCountry;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    private String overview;
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private ArrayList<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    private ProductionCountry[] productionCountries;
    private ArrayList<Season> seasons;
    @SerializedName("spoken_languages")
    private ArrayList<SpokenLanguage> spokenLanguages;
    private String status;
    private String tagline;
    private String type;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private long voteCount;

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public ArrayList<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public Object[] getEpisodeRunTime() {
        return episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public long getId() {
        return id;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public String[] getLanguages() {
        return languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }


    public String getName() {
        return name;
    }

    public Object getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public ArrayList<Networks> getNetworks() {
        return networks;
    }

    public double getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public double getNumberOfSeasons() {
        return numberOfSeasons;
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

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public ProductionCountry[] getProductionCountries() {
        return productionCountries;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public ArrayList<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getType() {
        return type;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
