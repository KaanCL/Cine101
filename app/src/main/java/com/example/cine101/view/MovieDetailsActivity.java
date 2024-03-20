package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.example.cine101.R;
import com.example.cine101.RoomData.WatchListEntity;
import com.example.cine101.ViewModel.MovieViewModel;
import com.example.cine101.ViewModel.WatchListViewModel;
import com.example.cine101.adapter.ActorAdapter;
import com.example.cine101.adapter.VideosAdapter;
import com.example.cine101.adapter.MovieDetailsActivity_Images_Adapter;
import com.example.cine101.databinding.ActivityMovieDetailsBinding;
import com.example.cine101.model.Tmdb.Cast;
import com.example.cine101.model.Tmdb.Genre;
import com.example.cine101.model.Tmdb.Images;
import com.example.cine101.model.Tmdb.ProductionCompany;
import com.example.cine101.model.Tmdb.VideosTMDB;
import com.example.cine101.model.Youtube.Items;
import com.example.cine101.model.Youtube.Snippet;
import com.example.cine101.model.Youtube.Video;
import com.example.cine101.repository.MovieRespository;
import com.example.cine101.util.Credentials;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

import static com.example.cine101.util.Credentials.API_KEY_YOUTUBE;
import static  com.example.cine101.util.Credentials.Video_Id;
import static com.example.cine101.util.Credentials.part;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding binding;
     int movieId;
     RecyclerView recyclerView_cast , recyclerView_images , recyclerView_fragmans;

     ActorAdapter movieDetailsActivityActorAdapter;
     MovieDetailsActivity_Images_Adapter movieDetailsActivityImagesAdapter;
     VideosAdapter videosAdapter;

    Retrofit retrofit;

    private  Snippet snippet;

    private  MovieRespository movieRespository = new MovieRespository();
    private MovieViewModel movieViewModel;
    private WatchListViewModel watchListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        watchListViewModel = new ViewModelProvider(this).get(WatchListViewModel.class);

        LiveData<List<WatchListEntity>> movies = watchListViewModel.getAllWatchList();

        movies.observe(this,watchListEntities -> {

            for(WatchListEntity e : watchListEntities){

                System.out.println(e.getTitle());

            }



        });








        Intent intent = getIntent();

       // movieId = intent.getIntExtra("movieId",-1);

     //   Credentials.setID(movieId);

        recyclerView_cast = binding.reyclerViewCast;
        recyclerView_images = findViewById(R.id.recyclerView_images);
        recyclerView_fragmans = binding.reyclerViewFragmans;

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        getMovieDetails();
        getMovieCast();
        getMovieImages();
        getMovieVideos();

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

            String saat =String.valueOf( movieDetails.getRuntime() / 60);
            String dakika = String.valueOf(movieDetails.getRuntime() % 60);

            String runtime = saat +"h"+dakika+"m";




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
            binding.movieRuntime.setText(runtime);
            String tagline = movieDetails.getTagline();
            if (tagline.length() > 70) {
                tagline = tagline.substring(0, 70) + "...";
            }
            binding.movieTagline.setText(tagline);
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
            movieDetailsActivityActorAdapter = new ActorAdapter(this,casts);
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
                   movieDetailsActivityActorAdapter = new ActorAdapter(casts);
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
            movieDetailsActivityImagesAdapter = new MovieDetailsActivity_Images_Adapter(this,images);
            recyclerView_images.setAdapter(movieDetailsActivityImagesAdapter);
        });}

    public void getMovieVideos(){
       movieViewModel.getVideosResponseLiveData().observe(this, videosResponse -> {
            ArrayList<VideosTMDB> videosTMDBS = videosResponse.getResults();
            ArrayList<ArrayList<Items>> itemList = new ArrayList<>();

           FragmentManager fragmentManager = getSupportFragmentManager();

            for (VideosTMDB e : videosTMDBS) {
                Video_Id = e.getKey();
                Credentials.setVideo_Id(Video_Id);

                LiveData<Video> videos = movieRespository.getYoutubeVideo(Video_Id, part, API_KEY_YOUTUBE);

                videos.observe(this, video -> {
                    ArrayList<Items> item = video.getItems();
                    itemList.add(item);
                    // Eğer itemList tamamlandıysa, recyclerView ve adapter ayarlaması yapılır.
                    if (itemList.size() == videosTMDBS.size()) {
                        System.out.println(itemList.size());
                        recyclerView_fragmans.setLayoutManager(new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        videosAdapter = new VideosAdapter(videosTMDBS, itemList, MovieDetailsActivity.this,fragmentManager);
                        recyclerView_fragmans.setAdapter(videosAdapter);
                    }

                });
            }


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