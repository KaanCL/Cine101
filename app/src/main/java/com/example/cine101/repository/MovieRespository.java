package com.example.cine101.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cine101.model.Tmdb.MovieDetails;
import com.example.cine101.model.Tmdb.VideosTMDB;
import com.example.cine101.model.Youtube.Items;
import com.example.cine101.model.Youtube.Snippet;
import com.example.cine101.model.Youtube.Video;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.ImagesResponse;
import com.example.cine101.responses.MovieResponse;
import com.example.cine101.responses.VideosResponse;
import com.example.cine101.service.RetrofitRequest;
import com.example.cine101.service.TmbdInterface;
import com.example.cine101.service.YoutubeInterface;
import com.example.cine101.util.Credentials;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MovieRespository {
    public static final String TAG = MovieRespository.class.getSimpleName();
    private TmbdInterface tmbdInterface;
    private YoutubeInterface youtubeInterface;
    private CompositeDisposable compositeDisposable;



    public MovieRespository() {
        tmbdInterface = RetrofitRequest.getRetrofitInstance().create(TmbdInterface.class);
        youtubeInterface = RetrofitRequest.getRetrofitInstance2().create(YoutubeInterface.class);
        compositeDisposable = new CompositeDisposable();

    }


    public LiveData<MovieResponse> getSearch_popular(String apikey, String language, int page, String region) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        compositeDisposable.add(tmbdInterface.getPopularMovies(apikey, language, page, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieResponse -> {
                    data.setValue(movieResponse);
                }));
        return data;

    }


public void clear(){
        compositeDisposable.clear();
}

    public LiveData<MovieResponse> getSearch_Trending(String apikey, String language) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        compositeDisposable.add(tmbdInterface.getTrendingMovies(apikey, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieResponse -> {
                            data.setValue(movieResponse);
                        }));
        return data;

    }


    public LiveData<MovieResponse> getSearch_inTheatre(String apikey, String language , int page , String region) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        compositeDisposable.add(tmbdInterface.getTheatresMovies(apikey, language , page ,region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieResponse -> {
                            data.setValue(movieResponse);
                        }));
        return data;
    }



    public LiveData<MovieResponse> getSearch_inUpcoming(String apikey, String language , int page , String region) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        compositeDisposable.add(tmbdInterface.getUpcomingMovies(apikey, language , page ,region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieResponse -> {
                            data.setValue(movieResponse);
                        }));
        return data;
    }

    public LiveData<MovieResponse> getSearch_inToprated(String apikey, String language , int page , String region) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        compositeDisposable.add(tmbdInterface.getTopratedMovies(apikey, language , page ,region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieResponse -> {
                            data.setValue(movieResponse);
                        }));
        return data;
    }




   /* public LiveData<MovieResponse>  getSearch_popular(String apikey , String language , int page , String region){
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        tmbdInterface.getPopularMovies(apikey , language , page ,region)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        Log.d(TAG,"onResponse response::" + response);

                        if(response.isSuccessful()){
                            data.setValue(response.body());

                            System.out.println("Movie Total Result" + response.body().getResults());

                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
        return data;
    }*/

 /*   public LiveData<MovieResponse> getSearch_Trending(String apiKey, String language){
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        tmbdInterface.getTrendingMovies(apiKey,language)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
        return data;
    }*/

   /* public LiveData<MovieResponse> getSearch_inTheatre(String apikey , String language , int page , String region){
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        tmbdInterface.getTheatresMovies(apikey , language , page ,region)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
     return  data;

    }*/

    public LiveData<MovieDetails> getMovieDetails(int movieId , String apiKey){
        final MutableLiveData<MovieDetails> data = new MutableLiveData<>();
        tmbdInterface.getMovieDetails(movieId ,apiKey)
                .enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });
        return  data;
    }

    public LiveData<CastResponse> getCast(int movieId , String apiKey){
        final MutableLiveData<CastResponse> data = new MutableLiveData<>();
        tmbdInterface.getCast(movieId , apiKey)
                .enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<CastResponse> call, Throwable t) {

                    }
                });
              return data;
    }

    public LiveData<ImagesResponse> getMovieImages(int movieId , String apiKey){
        final MutableLiveData<ImagesResponse> data = new MutableLiveData<>();
        tmbdInterface.getBackdrop( movieId , apiKey )
                .enqueue(new Callback<ImagesResponse>() {
                    @Override
                    public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {
                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ImagesResponse> call, Throwable t) {

                    }
                });
        return  data;

    }

    public LiveData<MovieResponse> getMovieSearchResult(String apikey , String query){
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        tmbdInterface.getSearchResult(apikey , query)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
        return  data;
    }


    public LiveData<VideosResponse> getMovieVideos(int movieId , String apiKey){
        final MutableLiveData<VideosResponse> data = new MutableLiveData<>();
        tmbdInterface.getMovieVideos(movieId,apiKey)
                .enqueue(new Callback<VideosResponse>() {
                    @Override
                    public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());


                            ArrayList<VideosTMDB> videosTMDBS = response.body().getResults();

                            for(VideosTMDB e : videosTMDBS){
                                Credentials.setVideo_Id(e.getKey());

                            }



                        }
                    }

                    @Override
                    public void onFailure(Call<VideosResponse> call, Throwable t) {

                    }
                });

        return  data;

    }

    public LiveData<Video> getYoutubeVideo(String videoID , String part , String apiKey){
        final MutableLiveData<Video> data = new MutableLiveData<>();
        youtubeInterface.getmovieVideos(videoID,part,apiKey)
                .enqueue(new Callback<Video>() {
                    @Override
                    public void onResponse(Call<Video> call, Response<Video> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());
                            System.out.println("İşlem Başarılı ! ");

                            for(Items e : response.body().getItems()){

                            }

                        }
                        System.out.println("İşlem Başarısız " + response.code());

                    }

                    @Override
                    public void onFailure(Call<Video> call, Throwable t) {

                    }
                });
        return data;
    }



}
