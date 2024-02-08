package com.example.cine101.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.cine101.R;
import com.example.cine101.model.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailsActivity_Images_Adapter extends RecyclerView.Adapter<MovieDetailsActivity_Images_Adapter.RowHolder> {


    private ArrayList<Images> images;

    public MovieDetailsActivity_Images_Adapter(ArrayList<Images> images) {this.images=images;}

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.images_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailsActivity_Images_Adapter.RowHolder holder, int position) {
        holder.bind(images.get(position),position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

   public class RowHolder extends  RecyclerView.ViewHolder{

        ImageView movieBrand;

       public RowHolder(@NonNull View itemView) {
           super(itemView);

       }

       public void bind(Images images , Integer position){

           movieBrand = itemView.findViewById(R.id.movie_images);
           Picasso.get().load("https://image.tmdb.org/t/p/original"+images.getFile_path()).into(movieBrand);

       }


   }


}
