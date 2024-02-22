package com.example.cine101.service;

import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.ImagesResponse;
import com.example.cine101.model.MovieDetails;
import com.example.cine101.responses.MovieResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.cine101.util.Credentials.API_KEY;

public interface TmbdInterface {


    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );

    @GET("trending/movie/day")
    Observable<MovieResponse> getTrendingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/now_playing")
    Observable<MovieResponse> getTheatresMovies(
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

    @GET("search/movie")
   Call<MovieResponse> getSearchResult(
            @Query("api_key") String apikey,
            @Query("query") String query
    );





}
