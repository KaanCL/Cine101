package com.example.cine101.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cine101.R;
import com.example.cine101.model.Tmdb.Season;
import com.example.cine101.util.Credentials;
import com.example.cine101.view.SeasonDetailsActivity;

import java.util.ArrayList;

public class SerieDetailsActivity_Seasons_Adapter extends RecyclerView.Adapter<SerieDetailsActivity_Seasons_Adapter.RowHolder> {

  private ArrayList<Season> seasons;
  private Context context;

    public SerieDetailsActivity_Seasons_Adapter(ArrayList<Season> seasons, Context context) {
        this.seasons = seasons;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerieDetailsActivity_Seasons_Adapter.RowHolder holder, int position) {
     holder.bind(seasons.get(position),position);

     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(holder.itemView.getContext(), SeasonDetailsActivity.class);
             Credentials.setSeasonNumber(seasons.get(position).getSeasonNumber());
             intent.putExtra("episodeCount",seasons.get(position).getEpisodeCount());
             System.out.println(seasons.get(position).getEpisodeCount());
             holder.itemView.getContext().startActivity(intent);
         }
     });



     //name , overview , voteAverage , posterPath , episodeCount


    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView rateTitle;
        ImageView brandImage;



        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Season season , Integer Positions){

            textTitle = itemView.findViewById(R.id.title_text);
            rateTitle = itemView.findViewById(R.id.rate_text);
            brandImage = itemView.findViewById(R.id.brand_image);

                String rate = Double.toString(season.getVoteAverage());
                String formattedRate = String.format("%.1f", Double.parseDouble(rate));

                rateTitle.setText(formattedRate);
                textTitle.setText(season.getName());

                Glide.with(context)
                        .load("https://image.tmdb.org/t/p/original" + season.getPosterPath())
                        .into(brandImage);

        }



    }

}
