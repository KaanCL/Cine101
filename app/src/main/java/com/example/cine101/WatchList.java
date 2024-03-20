package com.example.cine101;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cine101.view.MainActivity;
import com.example.cine101.view.PeopleActivity;
import com.example.cine101.view.SearchActivity;
import com.example.cine101.view.SerieActivity;

public class WatchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_watch_list);


    }


    public void openMovie(View view) {
        Intent intent = new Intent( WatchList.this, MainActivity.class);
        startActivity(intent);
    }

    public void openSerie(View view) {
        Intent intent = new Intent( WatchList.this,SerieActivity.class);
        startActivity(intent);
    }


    public void openWatchlist(View view) {
        Intent intent = new Intent(WatchList.this, WatchList.class);
        startActivity(intent);
    }


    public void openPeople(View view) {
        Intent intent = new Intent( WatchList.this, PeopleActivity.class);
        startActivity(intent);
    }

    public void openSearch(View view) {
        Intent intent = new Intent(WatchList.this, SearchActivity.class);
        startActivity(intent);
    }




}