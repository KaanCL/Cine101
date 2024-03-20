package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.RoomData.WatchListEntity;
import com.example.cine101.RoomData.WatchListRespository;

import java.util.List;

public class WatchListViewModel extends AndroidViewModel {

    private WatchListRespository watchListRespository;
    private LiveData<List<WatchListEntity>> allWatchList;

    public WatchListViewModel(Application application) {
        super(application);
        watchListRespository = new WatchListRespository(application);
        allWatchList = watchListRespository.getAllWatchList();
    }

    public void insert(WatchListEntity watchListEntity){watchListRespository.Insert(watchListEntity);}

    public void delete(WatchListEntity watchListEntity){watchListRespository.delete(watchListEntity);}

    public void deleteAll(){watchListRespository.AllDelete();}

    public LiveData<List<WatchListEntity>> getAllWatchList(){return allWatchList;}

}
