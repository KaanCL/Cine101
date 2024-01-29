package com.example.cine101.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cine101.R;
import com.example.cine101.model.CineModel;
import com.example.cine101.service.CineAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CineModel> cineModels;
    private String BASE_URL="https://www.omdbapi.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Retrofit && JSON

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData("ec8242cc","");

    }

    public void loadData(String apiKey,String movieTitle){
        CineAPI cineAPI = retrofit.create(CineAPI.class);
        Call<CineModel> call = cineAPI.getCine(apiKey,movieTitle);
        call.enqueue(new Callback<CineModel>() {
            @Override
            public void onResponse(Call<CineModel> call, Response<CineModel> response) {
                if(response.isSuccessful()){

                    CineModel cineModel = response.body();
                        ArrayList<CineModel> cineList = new ArrayList<>();
                        cineList.add(cineModel);

                    for (CineModel eleman : cineList) {
                        if(eleman.getTitle()!=null) {
                            System.out.println(eleman.getTitle());
                            System.out.println(eleman.getYear());
                            System.out.println(eleman.getType());
                            System.out.println(eleman.getPoster());
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<CineModel> call, Throwable t) {
                System.out.println("HATA: " + t);
            }
        });




    }

}