package com.example.cine101.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cine101.R;
import com.example.cine101.RoomData.WatchListDao;
import com.example.cine101.RoomData.WatchListDatabase;
import com.example.cine101.RoomData.WatchListEntity;
import com.example.cine101.RoomData.WatchListRespository;
import com.example.cine101.ViewModel.WatchListViewModel;
import com.example.cine101.model.Tmdb.Genre;
import com.example.cine101.model.Tmdb.Movie;
import com.example.cine101.util.Credentials;
import com.example.cine101.view.MovieDetailsActivity;

import java.util.ArrayList;

public class MainActivityMovie_Adapter extends RecyclerView.Adapter<MainActivityMovie_Adapter.RowHolder> {

    private ArrayList<Movie> movies ;
    private Context context;
    private WatchListViewModel watchListViewModel;
    public  WatchListEntity watchListEntity;
    private  Application application ;


    public MainActivityMovie_Adapter(Context context ,ArrayList<Movie> movies){
        this.context = context;
    this.movies = movies;
   ;}

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
             holder.addWatchList(movies.get(position),position);
             Credentials.setID(movies.get(position).getId());
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
        ImageView rateImage;


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
            rateImage = itemView.findViewById(R.id.imageView_rate);

            rateTitle.setText(formattedRate);

            textTitle.setText(movie.getTitle());
          //Picasso.get().load("https://image.tmdb.org/t/p/original" + movie.getPosterPath()).into(brandImage);
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                    .into(brandImage);
        }
        }

        public void addWatchList(Movie movie, Integer Position){

          //  WatchListRespository watchListRespository = new WatchListRespository(context);
           // WatchListDatabase database = WatchListDatabase.getInstance(application);
            //WatchListDao watchListDao = database.watchListDao();

            String id = String.valueOf(movie.getId()) ;
            String  title = String.valueOf(movie.getTitle());
            String  overView = String.valueOf(movie.getOverview());
            String date = String.valueOf(movie.getReleaseDate()) ;
            String rate =Double.toString(movie.getVoteAverage());
            String formattedRate=String.format("%.1f", Double.parseDouble(rate));

           // WatchListEntity watchList = new WatchListEntity(id,title,overView,date,formattedRate,"aksiyon");
          //  watchListRespository.Insert(watchList);
            //watchListDao.insert(watchList);



        }


    }


}
