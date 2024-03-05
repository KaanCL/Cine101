package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cine101.ViewModel.SeriesViewModel;
import com.example.cine101.adapter.ActorAdapter;
import com.example.cine101.adapter.SerieDetailsActivity_Seasons_Adapter;
import com.example.cine101.databinding.ActivitySerieDetailsBinding;
import com.example.cine101.model.Cast;
import com.example.cine101.model.CreatedBy;
import com.example.cine101.model.Genre;
import com.example.cine101.model.Networks;
import com.example.cine101.model.ProductionCompany;
import com.example.cine101.model.Season;
import com.example.cine101.util.Credentials;
import com.squareup.picasso.Picasso;
import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SerieDetailsActivity extends AppCompatActivity {

    ActivitySerieDetailsBinding binding;
    int serieId;
    RecyclerView recyclerView_seasons , recyclerView_cast;
    SerieDetailsActivity_Seasons_Adapter serieDetailsActivitySeasonsAdapter;
    ActorAdapter actorAdapter;

    private SeriesViewModel seriesViewModel;
    private  ArrayList<Season> seasons;
    private ArrayList<Cast> casts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySerieDetailsBinding.inflate(getLayoutInflater());
         View view =binding.getRoot();
        setContentView(view);

        recyclerView_seasons = binding.reyclerViewSeasons;
        recyclerView_cast = binding.recyclerViewCast;

        Intent intent = getIntent();

        serieId = intent.getIntExtra("serieId",-1);

        Credentials.setID(serieId);

        seriesViewModel = new ViewModelProvider(this).get(SeriesViewModel.class);

        getSerieDetails();
        getSerieActors();


    }

    public void getSerieDetails(){
        seriesViewModel.getSerieDetailsLiveData().observe(this,serieDetails -> {
            ArrayList<Genre> genres = serieDetails.getGenres();
            ArrayList<ProductionCompany> productionCompanies = serieDetails.getProductionCompanies();
            ArrayList<Networks> networks = serieDetails.getNetworks();
            ArrayList<CreatedBy> createdBies = serieDetails.getCreatedBy();

            String serieType_Text ="";
            String serieProduction_Text ="";
            String networks_Text="";




            for(Genre e: genres){
                serieType_Text+=e.getName()+" ";

            }

            for(ProductionCompany e: productionCompanies){
                serieProduction_Text+=e.getName()+" ";

            }

            for(Networks e : networks){
                networks_Text+=e.getName()+" ";
            }


            String rate = Double.toString(serieDetails.getVoteAverage());
            String formattedRate = String.format("%.1f", Double.parseDouble(rate));

            DecimalFormat decimalFormat = new DecimalFormat("#");

            binding.rateText.setText(formattedRate);
            Picasso.get().load("https://image.tmdb.org/t/p/original" + serieDetails.getBackdropPath()).into(binding.serieBrand);
            Picasso.get().load("https://image.tmdb.org/t/p/original" + serieDetails.getPosterPath()).into(binding.seriePoster);
            binding.serieTitle.setText(serieDetails.getName());
            binding.serieType.setText(serieType_Text);
            binding.serieTagline.setText(serieDetails.getTagline());
            binding.serieOverview.setText(serieDetails.getOverview());
            binding.serieStatus.setText(serieDetails.getStatus());
            binding.serieFirstAirDate.setText(serieDetails.getFirstAirDate());
            binding.serieLastAirDate.setText(serieDetails.getLastAirDate());
            binding.serieSeasons.setText(decimalFormat.format(serieDetails.getNumberOfSeasons()));
            binding.serieEpisodes.setText(decimalFormat.format(serieDetails.getNumberOfEpisodes()));
            binding.serieLanguage.setText(serieDetails.getOriginalLanguage());
            binding.serieNetworks.setText(networks_Text);
            binding.serieProductionCompanies.setText(serieProduction_Text);

            seasons = serieDetails.getSeasons();

            recyclerView_seasons.setLayoutManager(new LinearLayoutManager(SerieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            serieDetailsActivitySeasonsAdapter = new SerieDetailsActivity_Seasons_Adapter(seasons,this);
            recyclerView_seasons.setAdapter(serieDetailsActivitySeasonsAdapter);



        });
    }

    public void getSerieActors(){

        seriesViewModel.getCastResponseLiveData().observe(this,castResponse -> {

           casts = castResponse.getCast();

            recyclerView_cast.setLayoutManager(new LinearLayoutManager(SerieDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            actorAdapter = new ActorAdapter(this,casts);
            recyclerView_cast.setAdapter(actorAdapter);

        });



    }




}