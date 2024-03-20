package com.example.cine101.RoomData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {WatchListEntity.class} , version = 1)

public abstract class WatchListDatabase extends RoomDatabase {

    private static WatchListDatabase instance;

    public abstract WatchListDao watchListDao();

    public static WatchListDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), WatchListDatabase.class, "WatchList_database").build();
        }
        return instance;

    }


}
