package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cine101.R;
import com.example.cine101.ViewModel.MovieViewModel;
import com.example.cine101.adapter.MainActivityMovie_Adapter;
import com.example.cine101.responses.MovieResponse;
import com.example.cine101.model.Movie;
import com.example.cine101.service.RetrofitRequest;
import com.example.cine101.service.TmbdInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;



public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    Retrofit retrofit;
    RecyclerView recyclerView_popular, recyclerView_trending, recyclerView_theatre ;
    MainActivityMovie_Adapter mainActivityMovieAdapter;
    EditText editText;
    String title;
    ImageView searchSection;
    CompositeDisposable compositeDisposable;
    RetrofitRequest retrofitRequest;
    private MovieViewModel movieViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_popular = findViewById(R.id.recyclerView_popular);
        recyclerView_trending = findViewById(R.id.recyclerView_trending);
        recyclerView_theatre = findViewById(R.id.recyclerView_inTheatre);

        compositeDisposable = new CompositeDisposable();


        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        // getSearch_popular(API_KEY, language, page, region);
        //  getSearch_Trending(API_KEY, language);
        // getSearch_inTheatre(API_KEY, language, page, region);

        getMovie_Popular();
        getMovie_Trending();
        getMovie_Theatre();


    }

    private void getMovie_Popular(){
        movieViewModel.getSearch_popular_LiveData().observe(this, movieResponse -> {
            movies = (movieResponse.getResults());

            recyclerView_popular.setLayoutManager(new LinearLayoutManager(MainActivity.this ,LinearLayoutManager.HORIZONTAL,false));
            mainActivityMovieAdapter = new MainActivityMovie_Adapter(this,movies);
            recyclerView_popular.setAdapter(mainActivityMovieAdapter);

        });
    }

    private void getMovie_Trending(){
        movieViewModel.getSearch_trending_LiveData().observe(this,movieResponse -> {
            movies =(movieResponse.getResults());

            recyclerView_trending.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            mainActivityMovieAdapter = new MainActivityMovie_Adapter(this,movies);
            recyclerView_trending.setAdapter(mainActivityMovieAdapter);
        });


    }

    private void getMovie_Theatre(){
        movieViewModel.getSearch_theatre_LiveData().observe(this , movieResponse -> {
            movies = new ArrayList<Movie>(movieResponse.getResults());

            recyclerView_theatre.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            mainActivityMovieAdapter = new MainActivityMovie_Adapter(this,movies);
            recyclerView_theatre.setAdapter(mainActivityMovieAdapter);
        });
    }

    public void openSearch(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

   /* private void getSearch_popular(String apikey, String language, int page, String region) {

        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);

        compositeDisposable.add(tmbdInterface.getPopularMovies(apikey, language, page, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::popularResponse));

    }
    private void popularResponse(MovieResponse movieResponses) {
        movies = new ArrayList<Movie>(movieResponses.getResults());

        recyclerView_popular.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
        recyclerView_popular.setAdapter(mainActivityMovieAdapter);
    }


    public void getSearch_Trending(String apikey, String language) {

        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);

        compositeDisposable.add(tmbdInterface.getTrendingMovies(apikey, language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::trendingResponse));


    }

    private void trendingResponse(MovieResponse movieResponses) {
        movies = new ArrayList<Movie>(movieResponses.getResults());

        recyclerView_trending.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
        recyclerView_trending.setAdapter(mainActivityMovieAdapter);
    }


    public void getSearch_inTheatre(String apikey, String language, int page, String region) {

        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);

        compositeDisposable.add(tmbdInterface.getTheatresMovies(apikey, language, page, region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::theatreResponse));



    }

    private void theatreResponse(MovieResponse movieResponses) {
        movies = new ArrayList<Movie>(movieResponses.getResults());

        recyclerView_theatre.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
        recyclerView_theatre.setAdapter(mainActivityMovieAdapter);

    }


    public void openSearch(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

     public void getSearch_popular(String apikey , String language , int page , String region){
        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
        Call<MovieResponse> call = tmbdInterface.getPopularMovies(apikey, language, page, region);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    ArrayList<Movie> movies = movieResponse.getResults();

                    System.out.println("işlem başarılı");

                    recyclerView_popular.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
                    recyclerView_popular.setAdapter(mainActivityMovieAdapter);

                } else {
                    System.out.println("Hata: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Hata: " + t.getMessage());

            }
        });

    }


  /*  public void getSearch_Trending(String apikey , String language ){
        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
        Call<MovieResponse> call = tmbdInterface.getTrendingMovies(apikey,language);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    ArrayList<Movie> movies = movieResponse.getResults();

                    System.out.println("işlem başarılı");

                    recyclerView_trending.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
                    recyclerView_trending.setAdapter(mainActivityMovieAdapter);


                } else {
                    System.out.println("Hata: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Hata: " + t.getMessage());
            }
        });
    }*/

  /*  public void getSearch_inTheatre(String apikey , String language , int page ,String region ){
        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
        Call<MovieResponse> call = tmbdInterface.getTheatresMovies(apikey,language ,page ,region);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    ArrayList<Movie> movies = movieResponse.getResults();

                    recyclerView_theatre.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
                    recyclerView_theatre.setAdapter(mainActivityMovieAdapter);

                } else {
                    System.out.println("Hata: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Hata: " + t.getMessage());
            }
        });

    }

   /* public void testButton(View view){
        Intent intent = new Intent(getApplicationContext(),MovieDetailsActivity.class);
        startActivity(intent);

    }*/


}