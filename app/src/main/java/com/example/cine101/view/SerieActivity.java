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
import com.example.cine101.adapter.SeriesAdapter;
import com.example.cine101.model.Serie;

import java.util.ArrayList;

public class SerieActivity extends AppCompatActivity {

    ArrayList<Serie> series;
    RecyclerView recyclerView_popular, recyclerView_trending, recyclerView_onAir ,recyclerView_TopRated;
    private SeriesViewModel seriesViewModel;
    SeriesAdapter seriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        recyclerView_popular = findViewById(R.id.recyclerView_popular);
        recyclerView_trending = findViewById(R.id.recyclerView_trending);
        recyclerView_onAir = findViewById(R.id.recyclerView_onAir);
        recyclerView_TopRated = findViewById(R.id.recyclerView_TopRated);

        seriesViewModel = new ViewModelProvider(this).get(SeriesViewModel.class);
        getPopularSeries();
        getTrendingSeries();
        getOnairSeries();
        getTopratedSeries();
    }

    private void getPopularSeries(){
        seriesViewModel.getSeriesPopularLiveData().observe(this,serieResponse -> {
            series = (serieResponse.getResults());

            recyclerView_popular.setLayoutManager(new LinearLayoutManager(SerieActivity.this, LinearLayoutManager.HORIZONTAL, false));
            seriesAdapter = new SeriesAdapter(series,this);
            recyclerView_popular.setAdapter(seriesAdapter);
        } );

    }

    private void getTrendingSeries(){
        seriesViewModel.getSeriesTrendingLiveData().observe(this,serieResponse -> {
            series = (serieResponse.getResults());


            recyclerView_trending.setLayoutManager(new LinearLayoutManager(SerieActivity.this, LinearLayoutManager.HORIZONTAL, false));
            seriesAdapter = new SeriesAdapter(series,this);
            recyclerView_trending.setAdapter(seriesAdapter);
        } );

    }


    private void getOnairSeries(){
        seriesViewModel.getSeriesOnairLiveData().observe(this,serieResponse -> {
            series = (serieResponse.getResults());


            recyclerView_onAir.setLayoutManager(new LinearLayoutManager(SerieActivity.this, LinearLayoutManager.HORIZONTAL, false));
            seriesAdapter = new SeriesAdapter(series,this);
            recyclerView_onAir.setAdapter(seriesAdapter);
        } );

    }

    private void getTopratedSeries(){
        seriesViewModel.getSeriesTopratedLiveData().observe(this,serieResponse -> {
            series = (serieResponse.getResults());


            recyclerView_TopRated.setLayoutManager(new LinearLayoutManager(SerieActivity.this, LinearLayoutManager.HORIZONTAL, false));
            seriesAdapter = new SeriesAdapter(series,this);
            recyclerView_TopRated.setAdapter(seriesAdapter);
        } );

    }

    public void openMovie(View view) {
        Intent intent = new Intent( SerieActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void openPeople(View view) {
        Intent intent = new Intent( SerieActivity.this,PeopleActivity.class);
        startActivity(intent);
    }




}