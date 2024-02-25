package com.example.cine101.responses;

import com.example.cine101.model.Serie;
import java.util.ArrayList;
public class SerieResponse {
    private long page;
    private ArrayList<Serie> results;
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
}
