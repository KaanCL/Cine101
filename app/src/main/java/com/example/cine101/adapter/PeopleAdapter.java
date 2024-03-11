package com.example.cine101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cine101.R;
import com.example.cine101.model.Tmdb.People;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.RowHolder> {

    private ArrayList<People> peoples;
    private Context context;

    public PeopleAdapter(ArrayList<People> peoples, Context context) {
        this.peoples = peoples;
        this.context = context;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.people_layout , parent , false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
   holder.bind(peoples.get(position),position);
    }

    @Override
    public int getItemCount() {return peoples.size();}

    public class  RowHolder extends RecyclerView.ViewHolder{
        TextView textTitle ;
        TextView roleTitle;
        ImageView brandImage ;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(People people , Integer Position) {


            if (people.getName() != null || people.getProfilePath() != null) {

                textTitle = itemView.findViewById(R.id.title_text);
                roleTitle = itemView.findViewById(R.id.role_text);
                brandImage = itemView.findViewById(R.id.brand_image);

                textTitle.setText(people.getName());
                roleTitle.setText(people.getKnownForDepartment());

                Glide.with(context)
                        .load("https://image.tmdb.org/t/p/original" + people.getProfilePath())
                        .into(brandImage);
            }
        }


    }

}
