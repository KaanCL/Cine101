package com.example.cine101.model;

import java.util.ArrayList;

public class MovieResponse {

    private long page;
    private ArrayList<Movie> results;
    private ArrayList<MovieDetails> movieDetailsResult;
    private long totalPages;
    private long totalResults;


    public long getPage() { return page; }

    public void setPage(long value) { this.page = value; }


    public ArrayList<Movie> getResults() { return results; }

    public ArrayList<MovieDetails> getMovieDetailsResult(){return movieDetailsResult;}

    public long getTotalPages() { return totalPages; }

    public void setTotalPages(long value) { this.totalPages = value; }


    public long getTotalResults() { return totalResults; }

    public void setTotalResults(long value) { this.totalResults = value; }
}
