package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cine101.R;
import com.example.cine101.ViewModel.PeopleViewModel;
import com.example.cine101.adapter.PeopleAdapter;
import com.example.cine101.adapter.SeriesAdapter;
import com.example.cine101.model.Cast;
import com.example.cine101.model.People;

import java.util.ArrayList;

public class PeopleActivity extends AppCompatActivity {

   ArrayList<People> people;
   private PeopleViewModel peopleViewModel;
   RecyclerView recyclerView_popular, recyclerView_trending;
   PeopleAdapter peopleAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        peopleViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        recyclerView_popular = findViewById(R.id.recyclerView_popular);
        recyclerView_trending = findViewById(R.id.recyclerView_trending);

          getPopularPeople();
          getTrendingPeople();


    }


    public void getPopularPeople(){
        peopleViewModel.getPeoplePopularLiveData().observe(this ,peopleResponse -> {
            people = (peopleResponse.getResults());

            recyclerView_popular.setLayoutManager(new LinearLayoutManager(PeopleActivity.this, LinearLayoutManager.HORIZONTAL, false));
            peopleAdapter= new PeopleAdapter(people,this);
            recyclerView_popular.setAdapter(peopleAdapter);


        });



    }


    public void getTrendingPeople(){
   peopleViewModel.getPeopleTrendingLiveData().observe(this ,peopleResponse -> {
       people = (peopleResponse.getResults());

       recyclerView_trending.setLayoutManager(new LinearLayoutManager(PeopleActivity.this, LinearLayoutManager.HORIZONTAL, false));
       peopleAdapter= new PeopleAdapter(people,this);
       recyclerView_trending.setAdapter(peopleAdapter);


   });



    }

    public void openMovie(View view) {
        Intent intent = new Intent( PeopleActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void openSerie(View view) {
        Intent intent = new Intent( PeopleActivity.this,SerieActivity.class);
        startActivity(intent);
    }

    public void openSearch(View view) {
        Intent intent = new Intent(PeopleActivity.this, SearchActivity.class);
        startActivity(intent);
    }

}