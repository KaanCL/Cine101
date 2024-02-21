package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cine101.R;
import com.example.cine101.ViewModel.MovieViewModel;
import com.example.cine101.adapter.MainActivityMovie_Adapter;
import com.example.cine101.databinding.ActivitySearchBinding;
import com.example.cine101.model.Movie;
import com.example.cine101.responses.MovieResponse;
import com.example.cine101.service.TmbdInterface;
import com.example.cine101.util.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.MovieID;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;
import static  com.example.cine101.util.Credentials.Query;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    RecyclerView recyclerView_SearchResult;
    MainActivityMovie_Adapter mainActivityMovieAdapter;
    SearchView searchView;
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        compositeDisposable = new CompositeDisposable();

        recyclerView_SearchResult = binding.reyclerviewSearchResult;

        searchView = findViewById(R.id.searchBar);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Credentials.setQuery(newText);
                getSearch_Result(API_KEY,Query);
                return true;

            }
        });


    }

   /* public void getSearch_Result(){
        movieViewModel.getGetMovieSearchResult().observe(this ,movieResponse -> {

            ArrayList<Movie> movies = movieResponse.getResults();

            System.out.println("işlem başarılı");

            recyclerView_SearchResult.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));
            mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
            recyclerView_SearchResult.setAdapter(mainActivityMovieAdapter);

        });


    }*/


      public void getSearch_Result(String apikey , String query){

        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
        Call<MovieResponse> call = tmbdInterface.getSearchResult(apikey, query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    ArrayList<Movie> movies = movieResponse.getResults();

                    System.out.println("işlem başarılı");

                    recyclerView_SearchResult.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
                    recyclerView_SearchResult.setAdapter(mainActivityMovieAdapter);

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



    public void openMain(View view){
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
    }



 /*
        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);

        compositeDisposable.add(tmbdInterface.getSearchResult(api_key,query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::searchResponse)

        );


    }


    private void searchResponse(MovieResponse movieResponse){
        movies = new ArrayList<Movie>(movieResponse.getResults());


        recyclerView_SearchResult.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mainActivityMovieAdapter = new MainActivityMovie_Adapter(movies);
        recyclerView_SearchResult.setAdapter(mainActivityMovieAdapter);

    }*/




}