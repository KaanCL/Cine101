package com.example.cine101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cine101.R;
import com.example.cine101.model.Tmdb.Cast;

import java.util.ArrayList;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.RowHolder> {

    private ArrayList<Cast> cast;
    private Context context;

    public ActorAdapter(Context context , ArrayList<Cast> cast) {
        this.cast=cast;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cast_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.RowHolder holder, int position) {
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

          //  Picasso.get().load("https://image.tmdb.org/t/p/original" + cast.getProfilePath()).into(brandImage);

            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original" + cast.getProfilePath())
                    .into(brandImage);

            textName.setText(cast.getName());
            textRole.setText(cast.getCharacter());

        }

    }

}
