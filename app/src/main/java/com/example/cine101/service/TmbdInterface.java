package com.example.cine101.service;

import com.example.cine101.model.SerieDetails;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.ImagesResponse;
import com.example.cine101.model.MovieDetails;
import com.example.cine101.responses.MovieResponse;
import com.example.cine101.responses.PeopleResponse;
import com.example.cine101.responses.SerieResponse;

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


    @GET("movie/upcoming")
    Observable<MovieResponse> getUpcomingMovies(
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("region") String region
    );


    @GET("movie/top_rated")
    Observable<MovieResponse> getTopratedMovies(
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


   @GET("tv/popular")
    Observable<SerieResponse> getPopularSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("trending/tv/day")
    Observable<SerieResponse> getTrendingSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/airing_today")
    Observable<SerieResponse> getAiringSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/on_the_air")
    Observable<SerieResponse> getOnairSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("tv/top_rated")
    Observable<SerieResponse> getTopratedSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("person/popular")
    Observable<PeopleResponse> getPopularPeople(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );



    @GET("trending/person/day")
    Observable<PeopleResponse> getTrendingPeople(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    @GET("tv/{series_id}")
    Call<SerieDetails> getSerieDetail(
            @Path("series_id") int  series_id,
            @Query("api_key") String apikey
    );

    @GET("tv/{series_id}/credits")
    Call<CastResponse> getSerieCast(
            @Path("series_id") int movie_id,
            @Query("api_key") String apikey
    );




}
