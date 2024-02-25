package com.example.cine101.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cine101.R;
import com.example.cine101.model.Movie;
import com.example.cine101.view.MovieDetailsActivity;

import java.util.ArrayList;

public class MainActivityMovie_Adapter extends RecyclerView.Adapter<MainActivityMovie_Adapter.RowHolder> {

    private ArrayList<Movie> movies ;
    private Context context;

    public MainActivityMovie_Adapter(Context context ,ArrayList<Movie> movies){
    this.movies = movies;
    this.context = context;}

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MainActivityMovie_Adapter.RowHolder holder, int position) {

     holder.bind(movies.get(position) ,position);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
             intent.putExtra("movieId",movies.get(position).getId());
             holder.itemView.getContext().startActivity(intent);

         }
     });

    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class RowHolder extends  RecyclerView.ViewHolder{

        TextView textTitle ;
        TextView rateTitle;
        ImageView brandImage ;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind (Movie movie , Integer Position){

        if(movie.getTitle()!=null || movie.getPosterPath() != null) {

            String rate =Double.toString(movie.getVoteAverage());
            String formattedRate = String.format("%.1f", Double.parseDouble(rate));

          /* for(int i = 0 ; i<rate.length();i++){
               e+=rate.charAt(i);
               if(i>=2){
                   break;
               }
           }*/
            textTitle = itemView.findViewById(R.id.title_text);
            rateTitle = itemView.findViewById(R.id.rate_text);
            brandImage = itemView.findViewById(R.id.brand_image);

            rateTitle.setText(formattedRate);
            textTitle.setText(movie.getTitle());
          //Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getPosterPath()).into(brandImage);
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                    .into(brandImage);

        }

        }

    }
}
