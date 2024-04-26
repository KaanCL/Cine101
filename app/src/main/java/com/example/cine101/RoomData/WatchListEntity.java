package com.example.cine101.RoomData;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WatchList")
public class WatchListEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String movie_id;

    private String title;

    private String overView;

    private String date;

    private String rate;

    private String type;

    public WatchListEntity( String movie_id, String title, String overView, String date, String rate ,String type) {
        this.movie_id = movie_id;
        this.title = title;
        this.overView = overView;
        this.date = date;
        this.rate = rate;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }

    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }

    public String getType() {
        return type;
    }
}
