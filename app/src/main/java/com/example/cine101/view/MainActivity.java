package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cine101.R;
import com.example.cine101.adapter.MainActivityMovie_Adapter;
import com.example.cine101.model.MovieResponse;
import com.example.cine101.model.Movie;
import com.example.cine101.service.TmbdInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private String BASE_URL="https://api.themoviedb.org/3/";
    private final String api_key ="a42d32093e92ce5fc7277b527e8734b7";
    String language = "en-US";
    int page = 1;
    String region = "US";
    String time ="day";
    Retrofit retrofit;
    RecyclerView recyclerView_popular , recyclerView_trending , recyclerView_theatre;
    MainActivityMovie_Adapter mainActivityMovieAdapter;
    EditText editText;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_popular = findViewById(R.id.recyclerView_popular);
        recyclerView_trending = findViewById(R.id.recyclerView_trending);
        recyclerView_theatre = findViewById(R.id.recyclerView_inTheatre);

        //Retrofit && JSON

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        getSearch_popular(api_key,language,page,region);
        getSearch_Trending(api_key,language);
        getSearch_inTheatre(api_key,language,page,region);

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

    public void getSearch_Trending(String apikey , String language ){
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
    }
    public void getSearch_inTheatre(String apikey , String language , int page ,String region ){
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