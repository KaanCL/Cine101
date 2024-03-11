package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.model.Tmdb.MovieDetails;
import com.example.cine101.model.Youtube.Video;
import com.example.cine101.repository.MovieRespository;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.ImagesResponse;
import com.example.cine101.responses.MovieResponse;

import static com.example.cine101.util.Credentials.API_KEY_TMDB;
import static com.example.cine101.util.Credentials.API_KEY_YOUTUBE;
import static com.example.cine101.util.Credentials.ID;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.part;
import static com.example.cine101.util.Credentials.region;
import static  com.example.cine101.util.Credentials.Query;
import static  com.example.cine101.util.Credentials.Video_Id;

public class MovieViewModel extends AndroidViewModel {
    private MovieRespository movieRespository;
    private LiveData<MovieResponse> movieResponsePopularLiveData , movieResponseTrendingLiveData , movieResponseTheatreLiveData , movieResponseUpcomingLiveData  ,movieResponseTopratedLiveData ,getMovieSearchResult ;
    private LiveData<MovieDetails> getMovieDetailsLiveData;
    private LiveData<CastResponse> getCastLiveData;
    private LiveData<ImagesResponse> getMovieImagesLiveData;
    private LiveData<Video> getVideoLiveData;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRespository = new MovieRespository();
        movieResponsePopularLiveData = movieRespository.getSearch_popular(API_KEY_TMDB, language, page, region);
        movieResponseTrendingLiveData = movieRespository.getSearch_Trending(API_KEY_TMDB,language);
        movieResponseTheatreLiveData = movieRespository.getSearch_inTheatre(API_KEY_TMDB,language,page,region);
        movieResponseUpcomingLiveData  = movieRespository.getSearch_inUpcoming(API_KEY_TMDB,language,page,region);
        movieResponseTopratedLiveData = movieRespository.getSearch_inToprated(API_KEY_TMDB,language,page,region);
        getMovieDetailsLiveData = movieRespository.getMovieDetails(ID, API_KEY_TMDB);
        getCastLiveData = movieRespository.getCast(ID, API_KEY_TMDB);
        getMovieImagesLiveData = movieRespository.getMovieImages(ID, API_KEY_TMDB);
        getMovieSearchResult = movieRespository.getMovieSearchResult(API_KEY_TMDB, Query);
        getVideoLiveData = movieRespository.getMovieVideos(Video_Id , part ,API_KEY_YOUTUBE);


    }

    public LiveData<MovieResponse> getSearch_popular_LiveData(){return movieResponsePopularLiveData;
    }

    public LiveData<MovieResponse> getSearch_trending_LiveData(){return movieResponseTrendingLiveData;
    }

    public LiveData<MovieResponse> getSearch_theatre_LiveData(){return movieResponseTheatreLiveData;
    }

    public LiveData<MovieDetails> getGetMovieDetailsLiveData() {
        return getMovieDetailsLiveData;
    }

    public LiveData<CastResponse> getGetCastLiveData() {
        return getCastLiveData;
    }

    public LiveData<ImagesResponse> getGetMovieImagesLiveData() {
        return getMovieImagesLiveData;
    }

    public LiveData<MovieResponse> getGetMovieSearchResult() {
        return getMovieSearchResult;
    }

    public LiveData<MovieResponse> getMovieResponseUpcomingLiveData() {
        return movieResponseUpcomingLiveData;
    }

    public LiveData<MovieResponse> getMovieResponseTopratedLiveData() {
        return movieResponseTopratedLiveData;
    }

    public LiveData<Video> getGetVideoLiveData() {
        return getVideoLiveData;
    }
}
