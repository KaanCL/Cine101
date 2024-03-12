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
import com.example.cine101.model.Tmdb.VideosTMDB;
import com.example.cine101.model.Youtube.Items;
import com.example.cine101.model.Youtube.Snippet;
import com.example.cine101.model.Youtube.Thumbnails;
import com.example.cine101.R;
import com.example.cine101.responses.VideosResponse;

import java.util.ArrayList;

public class VideosAdapter extends  RecyclerView.Adapter<VideosAdapter.RowHolder>{
    private ArrayList<VideosTMDB> videoTMBD;
    private ArrayList<ArrayList<Items>>items;

    private Context context;
    private Thumbnails thumbnails;

    public VideosAdapter(ArrayList<VideosTMDB> videoTMBD, ArrayList<ArrayList<Items>> items, Context context) {
        this.videoTMBD = videoTMBD;
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragman_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        ArrayList<Items> itemList = items.get(position);

        for(Items item :itemList){
            holder.bind(videoTMBD.get(position),item, position);
        }

    }

    @Override
    public int getItemCount() {
        return videoTMBD.size();
    }

    public class  RowHolder extends RecyclerView.ViewHolder{
        TextView textName;
        ImageView brandImage;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.fragman_title);
            brandImage = itemView.findViewById(R.id.fragman_brand);
        }

        public void bind(VideosTMDB videosTMDB ,Items item ,Integer position){



            textName.setText(videosTMDB.getName());


           /* textName = itemView.findViewById(R.id.fragman_title);
            brandImage = itemView.findViewById(R.id.fragman_brand);

            textName.setText(videoTMBD.getSnippet().getTitle());

            System.out.println(videoTMBD.getSnippet().getDescription());
            thumbnails = videoTMBD.getSnippet().getThumbnails();*/

            //System.out.println(items.getSnippet().getTitle());

            Glide.with(context)
                    .load(item.getSnippet().getThumbnails().getStandart().getUrl())
                    .into(brandImage);


        }

    }


}
