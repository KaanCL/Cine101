package com.example.cine101.view;

import static retrofit2.converter.gson.GsonConverterFactory.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import com.example.cine101.R;
import com.example.cine101.adapter.MovieDetailsActivity_Actor_Adapter;
import com.example.cine101.databinding.ActivityMainBinding;
import com.example.cine101.databinding.ActivityMovieDetailsBinding;
import com.example.cine101.model.Cast;
import com.example.cine101.model.CastResponse;
import com.example.cine101.model.Genre;
import com.example.cine101.model.Movie;
import com.example.cine101.model.MovieDetails;
import com.example.cine101.model.ProductionCompany;
import com.example.cine101.service.TmbdInterface;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding binding;
     int movieId;
     RecyclerView recyclerView_cast;
     MovieDetailsActivity_Actor_Adapter movieDetailsActivityActorAdapter;


    private String BASE_URL="https://api.themoviedb.org/3/";
    private final String api_key = "a42d32093e92ce5fc7277b527e8734b7";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Intent intent = getIntent();

        movieId = intent.getIntExtra("movieId",-1);

        recyclerView_cast = findViewById(R.id.reyclerView_cast);



        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        getMovieDetails(api_key,movieId);
        getMovieCast(api_key,movieId);

    }

    public void getMovieDetails(String apikey,int id){
        TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
        Call<MovieDetails> call = tmbdInterface.getMovieDetails(id,apikey);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                if(response.isSuccessful()){
                    MovieDetails movieDetails = response.body();
                    ArrayList<Genre> genres = movieDetails.getGenres();
                    ArrayList<ProductionCompany> productionCompanies = movieDetails.getProduction_companies();

                    String movieType_Text = "";
                    String movieProduction_Text = "";

                    for (Genre e : genres){
                        movieType_Text+=e.getName() + " ";
                    }

                    for(ProductionCompany e : productionCompanies){
                        movieProduction_Text+=e.getName();
                    }




                    Picasso.get().load("https://image.tmdb.org/t/p/original" + movieDetails.getBackdrop_path()).into(binding.movieBrand);
                    Picasso.get().load("https://image.tmdb.org/t/p/original" + movieDetails.getPoster_path()).into(binding.moviePoster);
                    binding.movieTitle.setText(movieDetails.getTitle());
                    binding.movieType.setText(movieType_Text);
                    binding.movieTagline.setText(movieDetails.getTagline());
                    binding.movieOverview.setText(movieDetails.getOverview());
                    binding.movieStatus.setText(movieDetails.getStatus());
                    binding.movieDate.setText(movieDetails.getRelease_date());
                    binding.movieLanguage.setText(movieDetails.getOriginal_language());
                    binding.movieBudget.setText("$"+movieDetails.getBudget());
                    binding.movieRevenue.setText("$"+movieDetails.getRevenue());
                    binding.movieProductionCompanies.setText(movieProduction_Text);




                }else {
                    System.out.println("HATA");
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                System.out.println("HATA: " + t);
            }
        });

    }

   public void getMovieCast(String apikey , int id) {
       TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
       Call<CastResponse> call = tmbdInterface.getCast(id, apikey);
       call.enqueue(new Callback<CastResponse>() {
           @Override
           public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {

               if(response.isSuccessful()){
                   CastResponse castResponse = response.body();
                   ArrayList<Cast> casts = castResponse.getCast();

                   recyclerView_cast.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
                   movieDetailsActivityActorAdapter = new MovieDetailsActivity_Actor_Adapter(casts);
                   recyclerView_cast.setAdapter(movieDetailsActivityActorAdapter);

               }else {

                   System.out.println("HATA");
               }
           }
           @Override
           public void onFailure(Call<CastResponse> call, Throwable t) {
               System.out.println("HATA: " + t);
           }
       });


   }

}