package com.example.cine101.responses;

import com.example.cine101.model.Tmdb.Serie;
import com.example.cine101.model.Tmdb.SerieDetails;

import java.util.ArrayList;
public class SerieResponse {
    private long page;
    private ArrayList<Serie> results;
    private ArrayList<SerieDetails> serieDetailsResult;
    private long totalPages;
    private long totalResults;


    public long getPage() {
        return page;
    }

    public ArrayList<Serie> getResults() {
        return results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public ArrayList<SerieDetails> getSerieDetailsResult() {
        return serieDetailsResult;
    }
}
