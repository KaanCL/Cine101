package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.example.cine101.R;
import com.example.cine101.ViewModel.MovieViewModel;
import com.example.cine101.adapter.MovieDetailsActivity_Actor_Adapter;
import com.example.cine101.adapter.MovieDetailsActivity_Images_Adapter;
import com.example.cine101.databinding.ActivityMovieDetailsBinding;
import com.example.cine101.model.Cast;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.model.Genre;
import com.example.cine101.model.Images;
import com.example.cine101.responses.ImagesResponse;
import com.example.cine101.model.MovieDetails;
import com.example.cine101.model.ProductionCompany;
import com.example.cine101.responses.MovieResponse;
import com.example.cine101.service.RetrofitRequest;
import com.example.cine101.service.TmbdInterface;
import com.example.cine101.util.Credentials;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.MovieID;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;
import static  com.example.cine101.util.Credentials.Query;



public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding binding;
     int movieId;
     RecyclerView recyclerView_cast , recyclerView_images;

     MovieDetailsActivity_Actor_Adapter movieDetailsActivityActorAdapter;
     MovieDetailsActivity_Images_Adapter movieDetailsActivityImagesAdapter;

    Retrofit retrofit;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Intent intent = getIntent();

        movieId = intent.getIntExtra("movieId",-1);

        Credentials.setMovieID(movieId);

        recyclerView_cast = binding.reyclerViewCast;
        recyclerView_images = findViewById(R.id.recyclerView_images);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        //apiRequest = RetrofitRequest.getRetrofitInstance().create(TmbdInterface.class);

       getMovieDetails();
        getMovieCast();
        getMovieImages();

    }


      public void getMovieDetails(){
        movieViewModel.getGetMovieDetailsLiveData().observe(this , movieDetails -> {

            ArrayList<Genre> genres = movieDetails.getGenres();
            ArrayList<ProductionCompany> productionCompanies = movieDetails.getProduction_companies();

            String movieType_Text = "";
            String movieProduction_Text = "";

            for (Genre e : genres){
                movieType_Text+=e.getName() + " ";
            }

            for(ProductionCompany e : productionCompanies){
                movieProduction_Text+=e.getName() + " ";
            }

            String rate =Double.toString(movieDetails.getVote_average());
            String formattedRate = String.format("%.1f", Double.parseDouble(rate));

            binding.rateText.setText(formattedRate);


            if(movieDetails.getBudget()!=0 || movieDetails.getRevenue()!=0){
                binding.movieBudget.setText("$"+movieDetails.getBudget());
                binding.movieRevenue.setText("$"+movieDetails.getRevenue());
            }else{
                binding.movieBudget.setText("Budget not available");
                binding.movieRevenue.setText("Revenue not available");
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
            binding.movieProductionCompanies.setText(movieProduction_Text);

        });




    }

 /* public void getMovieDetails(String apikey,int id){
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
                        movieProduction_Text+=e.getName() + " ";
                    }

                    String rate =Double.toString(movieDetails.getVote_average());
                    String formattedRate = String.format("%.1f", Double.parseDouble(rate));

                    binding.rateText.setText(formattedRate);


                    if(movieDetails.getBudget()!=0 || movieDetails.getRevenue()!=0){
                        binding.movieBudget.setText("$"+movieDetails.getBudget());
                        binding.movieRevenue.setText("$"+movieDetails.getRevenue());
                    }else{
                        binding.movieBudget.setText("Budget not available");
                        binding.movieRevenue.setText("Revenue not available");
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

    }*/

    public void getMovieCast(){
        movieViewModel.getGetCastLiveData().observe(this ,castResponse -> {
            ArrayList<Cast> casts = castResponse.getCast();

            recyclerView_cast.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            movieDetailsActivityActorAdapter = new MovieDetailsActivity_Actor_Adapter(casts);
            recyclerView_cast.setAdapter(movieDetailsActivityActorAdapter);
        });
    }


  /* public void getMovieCast(String apikey , int id) {
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


   }*/
    public void getMovieImages(){
        movieViewModel.getGetMovieImagesLiveData().observe(this , imagesResponse -> {
            ArrayList<Images> images = imagesResponse.getBackdrops();

            recyclerView_images.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            movieDetailsActivityImagesAdapter = new MovieDetailsActivity_Images_Adapter(images);
            recyclerView_images.setAdapter(movieDetailsActivityImagesAdapter);
        });

    }

   /*public void getMovieImages(String apikey ,int id){
       TmbdInterface tmbdInterface = retrofit.create(TmbdInterface.class);
       Call<ImagesResponse> call = tmbdInterface.getBackdrop(id,apikey);
       call.enqueue(new Callback<ImagesResponse>() {
           @Override
           public void onResponse(Call<ImagesResponse> call, Response<ImagesResponse> response) {

               if(response.isSuccessful()){
                   ImagesResponse imagesResponse = response.body();
                   ArrayList<Images> images = imagesResponse.getBackdrops();

                   recyclerView_images.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
                   movieDetailsActivityImagesAdapter = new MovieDetailsActivity_Images_Adapter(images);
                   recyclerView_images.setAdapter(movieDetailsActivityImagesAdapter);



               }else{

                   System.out.println("HATA !");
               }


           }

           @Override
           public void onFailure(Call<ImagesResponse> call, Throwable t) {

           }
       });

   }*/

}