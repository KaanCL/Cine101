package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cine101.R;
import com.example.cine101.ViewModel.SeriesViewModel;
import com.example.cine101.adapter.SeasonActivityEpisodes_Adapter;
import com.example.cine101.adapter.SerieDetailsActivity_Seasons_Adapter;
import com.example.cine101.databinding.ActivitySeasonDetailsBinding;
import com.example.cine101.model.Episodes;
import com.example.cine101.util.Credentials;
import com.squareup.picasso.Picasso;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;
import static com.example.cine101.util.Credentials.seasonNumber;
import static com.example.cine101.util.Credentials.brand;

import java.util.ArrayList;

public class SeasonDetailsActivity extends AppCompatActivity {

    ActivitySeasonDetailsBinding binding;
    int serieId , seasonNumber;
    RecyclerView recyclerView_episodes;
    SeasonActivityEpisodes_Adapter seasonActivityEpisodesAdapter;

    private SeriesViewModel seriesViewModel;
    private ArrayList<Episodes> episodes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySeasonDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recyclerView_episodes = binding.reyclerViewEpisodes;

        seriesViewModel= new ViewModelProvider(this).get(SeriesViewModel.class);


        System.out.println(Credentials.getSeasonNumber());

        getSeasonDetails();
    }

    public void getSeasonDetails(){
       seriesViewModel.getSeasonDetailsLiveData().observe(this,seasonDetails -> {
           Intent intent = getIntent();

           String rate = Double.toString(seasonDetails.getVote_average());
           String formattedRate = String.format("%.1f", Double.parseDouble(rate));


           Picasso.get().load("https://image.tmdb.org/t/p/original" + seasonDetails.getPoster_path()).into(binding.seasonPoster);
           Picasso.get().load("https://image.tmdb.org/t/p/original" + brand).into(binding.seasonBrand);
           binding.seasonTitle.setText(seasonDetails.getName());
           binding.rateText.setText(formattedRate);
           binding.seasonYear.setText(seasonDetails.getAir_date());

           System.out.println("overview"+seasonDetails.getOverview());
           if(seasonDetails.getOverview()!=""){
               binding.textView6.setText("Overview");
               binding.textView6.setVisibility(1);
               binding.seasonOverview.setText(seasonDetails.getOverview());
           }

           episodes = seasonDetails.getEpisodes();
          binding.seasonEpisodes.setText(String.valueOf(episodes.size()));
           recyclerView_episodes.setLayoutManager(new LinearLayoutManager(SeasonDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
           seasonActivityEpisodesAdapter = new SeasonActivityEpisodes_Adapter(episodes,this);
           recyclerView_episodes.setAdapter(seasonActivityEpisodesAdapter);


       });

    }

}