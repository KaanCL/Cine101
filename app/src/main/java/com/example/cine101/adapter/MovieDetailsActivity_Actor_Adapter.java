package com.example.cine101.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cine101.R;
import com.example.cine101.model.Cast;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieDetailsActivity_Actor_Adapter  extends RecyclerView.Adapter<MovieDetailsActivity_Actor_Adapter.RowHolder> {

    private ArrayList<Cast> cast;

    public MovieDetailsActivity_Actor_Adapter(ArrayList<Cast> cast) {this.cast=cast;}

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cast_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailsActivity_Actor_Adapter.RowHolder holder, int position) {
   holder.bind(cast.get(position),position);
    }

    @Override
    public int getItemCount() {return  cast.size();}


    public class RowHolder  extends  RecyclerView.ViewHolder{
        TextView textName;
        TextView textRole;
        ImageView brandImage;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind (Cast cast , Integer position){
            textName=itemView.findViewById(R.id.actor_name);
            textRole=itemView.findViewById(R.id.actor_role);
            brandImage=itemView.findViewById(R.id.actor_brand);

            Picasso.get().load("https://image.tmdb.org/t/p/original" + cast.getProfilePath()).into(brandImage);
            textName.setText(cast.getName());
            textRole.setText(cast.getCharacter());

        }

    }

}
