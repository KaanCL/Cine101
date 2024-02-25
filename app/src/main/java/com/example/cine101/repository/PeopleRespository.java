package com.example.cine101.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cine101.responses.PeopleResponse;
import com.example.cine101.service.RetrofitRequest;
import com.example.cine101.service.TmbdInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PeopleRespository {

    private TmbdInterface tmbdInterface;
    private CompositeDisposable compositeDisposable;

    public PeopleRespository() {
       tmbdInterface = RetrofitRequest.getRetrofitInstance().create(TmbdInterface.class);
       compositeDisposable = new CompositeDisposable();
    }

  public LiveData<PeopleResponse> getPopularPeople(String apikey , String language){
          final MutableLiveData<PeopleResponse> data = new MutableLiveData<>();
          compositeDisposable.add(tmbdInterface.getPopularPeople(apikey , language)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(peopleResponse ->
                          data.setValue(peopleResponse)));

          return  data;
  }

  public LiveData<PeopleResponse> getTrendingPeople(String apikey , String language){
        final  MutableLiveData<PeopleResponse> data = new MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getTrendingPeople(apikey , language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(peopleResponse ->
                        data.setValue(peopleResponse)
                        ));

      return  data;
  }



}
