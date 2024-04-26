package com.example.cine101.RoomData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

@Dao
public interface WatchListDao {

   @Insert
   Completable insert (WatchListEntity watchListEntity);

   @Delete
   Completable delete (WatchListEntity watchListEntity);

   @Query("Delete From Watchlist")
    Completable deleteAllWatchList();

   @Query("SELECT * FROM Watchlist")
   List<WatchListEntity> getAllWatchList();

}
