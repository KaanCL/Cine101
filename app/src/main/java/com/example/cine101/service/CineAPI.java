package com.example.cine101.service;

import com.example.cine101.model.CineModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CineAPI {

    @GET("/")
    Call<CineModel> getCine(@Query("apikey") String apiKey, @Query("t") String searchQuery);
}
