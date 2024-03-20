package com.example.cine101.RoomData;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListRespository {

    private WatchListDao watchListDao;
    private LiveData<List<WatchListEntity>> allWatchList;
    private CompositeDisposable compositeDisposable;

    public WatchListRespository(Application application) {
        WatchListDatabase database = WatchListDatabase.getInstance(application);
        watchListDao = database.watchListDao();
        allWatchList = watchListDao.getAllWatchList();
        compositeDisposable = new CompositeDisposable();
    }


    public void Insert(WatchListEntity watchListEntity){
        compositeDisposable.add(watchListDao.insert(watchListEntity)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(()->{
                            System.out.println("Film Başarıyla Eklendi");
                        }));
    }

    public void delete (WatchListEntity watchListEntity){
        compositeDisposable.add(watchListDao.delete(watchListEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    System.out.println("Film Başarıyla Silindi");
                }));
    }

    public void AllDelete(){
        compositeDisposable.add(watchListDao.deleteAllWatchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }
    public LiveData<List<WatchListEntity>> getAllWatchList(){return allWatchList;}
}

