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
import com.example.cine101.model.Episodes;
import com.example.cine101.model.Season;
import com.example.cine101.model.SeasonDetails;

import java.util.ArrayList;

public class SeasonActivityEpisodes_Adapter extends RecyclerView.Adapter<SeasonActivityEpisodes_Adapter.RowHolder>{

    private ArrayList<Episodes> episodes ;
    private Context context;

    public SeasonActivityEpisodes_Adapter(ArrayList<Episodes> episodes, Context context) {
        this.episodes = episodes;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.episode_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(episodes.get(position),position);


    }

    @Override
    public int getItemCount() {return episodes.size();}

    public class RowHolder extends RecyclerView.ViewHolder{
        TextView Title_Text ;
        TextView Rate_Text ;
        TextView Year_Text ;
        TextView Minute_Text ;
        TextView Overview_Text ;
        ImageView brand;

        public RowHolder(@NonNull View itemView) {super(itemView);}

        public void bind(Episodes episodes , Integer Positions){

            Title_Text = itemView.findViewById(R.id.episode_title);
            Rate_Text = itemView.findViewById(R.id.rate_text);
            Year_Text = itemView.findViewById(R.id.episode_year);
            Minute_Text = itemView.findViewById(R.id.episode_minute);
            Overview_Text = itemView.findViewById(R.id.episode_overview);
            brand = itemView.findViewById(R.id.episode_brand);



            String rate = Double.toString(episodes.getVote_average());
            String formattedRate = String.format("%.1f", Double.parseDouble(rate));

            String runtime = Double.toString(episodes.getRuntime());
            String formattedRuntime = String.format("%.1f",Double.parseDouble(runtime));

            Title_Text.setText(episodes.getName());
            Rate_Text.setText(formattedRate);
            Year_Text.setText(episodes.getAir_date());
            Minute_Text.setText(formattedRuntime + "m");

            String overview = episodes.getOverview();
            if (overview.length() > 120) {
                overview = overview.substring(0, 120) + "...";
            }
            Overview_Text.setText(overview);
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original" + episodes.getStill_path())
                    .into(brand);

        }

    }



}
