package com.example.cine101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cine101.VideoFragment;
import com.example.cine101.model.Tmdb.VideosTMDB;
import com.example.cine101.model.Youtube.Items;
import com.example.cine101.model.Youtube.Thumbnails;
import com.example.cine101.R;
import com.example.cine101.util.Credentials;

import java.util.ArrayList;

public class VideosAdapter extends  RecyclerView.Adapter<VideosAdapter.RowHolder>{
    private ArrayList<VideosTMDB> videoTMBD;
    private ArrayList<ArrayList<Items>>items;

    private Context context;
    private Thumbnails thumbnails;
    private FrameLayout frameLayout;
    private String url = " ";
    private  Credentials credentials = new Credentials();
    private FragmentManager fragmentManager ;
    private FragmentTransaction fragmentTransaction;


    public String getUrl() {
        return url;
    }

    private VideoFragment videoFragment = new VideoFragment();

    public VideosAdapter(ArrayList<VideosTMDB> videoTMBD, ArrayList<ArrayList<Items>> items, Context context,FragmentManager fragmentManager) {
        this.videoTMBD = videoTMBD;
        this.items = items;
        this.context = context;
        this.fragmentManager =fragmentManager;
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
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
               public void onClick(View v) {
                    credentials.setVideo_url(item.getId());
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmanWidget,videoFragment);
                    fragmentTransaction.commit();
                    FrameLayout frameLayout=holder.itemView.getRootView().findViewById(R.id.fragmanWidget);
                    if (frameLayout != null) {
                        frameLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return videoTMBD.size();
    }

    public class  RowHolder extends RecyclerView.ViewHolder{
        TextView textName;
        ImageView brandImage;
        Thumbnails thumbnails;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.fragman_title);
            brandImage = itemView.findViewById(R.id.fragman_brand);
        }

        public void bind(VideosTMDB videosTMDB, Items item, Integer position) {


            if (videosTMDB != null && item.getSnippet().getThumbnails()!= null) {

                textName.setText(videosTMDB.getName());

                thumbnails = item.getSnippet().getThumbnails();

                String default_url = "";
                String medium_url = "";
                String standart_url = "";

                int count = 0;

                if(thumbnails.getThumbnaildefault() != null) {
                    default_url = thumbnails.getThumbnaildefault().getUrl();
                    count++;
                }
                if(thumbnails.getMedium() != null) {
                    medium_url = thumbnails.getMedium().getUrl();
                    count++;
                }
                if(thumbnails.getStandart() != null) {
                    standart_url= thumbnails.getStandart().getUrl();
                    count++;
                }

               String url = default_url;

               switch (count){

                   case 1:
                       url = default_url;
                       break;
                   case 2:
                       url = medium_url;
                       break;
                   case 3:
                       url = standart_url;
                       break;

                   default:
                       url = "";
               }

                System.out.println(url);

                    Glide.with(context)
                            .load(url)
                            .into(brandImage);
            }
        }

    }


}
