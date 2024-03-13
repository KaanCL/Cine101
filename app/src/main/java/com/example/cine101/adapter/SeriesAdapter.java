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
import com.example.cine101.model.Tmdb.Serie;
import com.example.cine101.util.Credentials;
import com.example.cine101.view.SerieDetailsActivity;

import java.util.ArrayList;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.RowHolder> {

    private ArrayList<Serie> series;
    private Context context;

    public SeriesAdapter(ArrayList<Serie> series, Context context) {
        this.series = series;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(series.get(position),position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SerieDetailsActivity.class);
              // intent.putExtra("serieId",series.get(position).getId());
                Credentials.setID(series.get(position).getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {return series.size();
    }

    public class  RowHolder extends RecyclerView.ViewHolder{

        TextView textTitle ;
        TextView rateTitle;
        ImageView brandImage ;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind (Serie serie, Integer Position){

            String rate = Double.toString(serie.getVoteAverage());
            String formattedRate = String.format("%.1f", Double.parseDouble(rate));

            textTitle = itemView.findViewById(R.id.title_text);
            rateTitle = itemView.findViewById(R.id.rate_text);
            brandImage = itemView.findViewById(R.id.brand_image);
            rateTitle.setText(formattedRate);
            textTitle.setText(serie.getName());

            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original" + serie.getPosterPath())
                    .into(brandImage);

        }


    }

}
