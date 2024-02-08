package com.example.cine101.service;
import com.example.cine101.model.Cast;
import com.example.cine101.model.CastResponse;
import com.example.cine101.model.ImagesResponse;
import com.example.cine101.model.MovieDetails;
import com.example.cine101.model.MovieResponse;
import com.example.cine101.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmbdInterface {

     final String api_key ="a42d32093e92ce5fc7277b527e8734b7";


    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );

    @GET("trending/movie/day")
    Call<MovieResponse> getTrendingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/now_playing")
    Call<MovieResponse> getTheatresMovies(
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(
            @Path("movie_id") int  movie_id,
            @Query("api_key") String apikey
        );


    @GET("movie/{movie_id}/credits")
     Call<CastResponse> getCast(
            @Path("movie_id") int movie_id,
            @Query("api_key") String apikey
    );

    @GET("movie/{movie_id}/images")
    Call<ImagesResponse> getBackdrop(
            @Path("movie_id") int movie_id,
            @Query("api_key") String apikey
    );



}
