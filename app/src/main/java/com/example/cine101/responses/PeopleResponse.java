package com.example.cine101.responses;

import com.example.cine101.model.Cast;
import com.example.cine101.model.People;

import java.util.ArrayList;

public class PeopleResponse {

    private double page;
    private ArrayList<People> results;
    private double totalPages;
    private double totalResults;


    public double getPage() {
        return page;
    }

    public ArrayList<People> getResults() {
        return results;
    }

    public double getTotalPages() {
        return totalPages;
    }

    public double getTotalResults() {
        return totalResults;
    }
}
