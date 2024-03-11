package com.example.cine101.service;
import com.example.cine101.model.Youtube.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeInterface {

    @GET("videos")
    Call<Video> getmovieVideos(
            @Query("id") String videoId,
            @Query("part") String part,
            @Query("key") String apiKey
    );

}
