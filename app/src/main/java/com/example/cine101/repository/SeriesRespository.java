package com.example.cine101.repository;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cine101.model.SerieDetails;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.SerieResponse;
import com.example.cine101.service.RetrofitRequest;
import com.example.cine101.service.TmbdInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesRespository {

    private TmbdInterface tmbdInterface;
    private CompositeDisposable compositeDisposable;

    public SeriesRespository() {
    tmbdInterface = RetrofitRequest.getRetrofitInstance().create(TmbdInterface.class);
    compositeDisposable = new CompositeDisposable();
    }

    public LiveData<SerieResponse> getPopularSeries(String apikey , String language , int page){
        final MutableLiveData<SerieResponse> data = new MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getPopularSeries(apikey,language,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(seriesResponse ->
                        data.setValue(seriesResponse)));
        return data;
    }


  public LiveData<SerieResponse> getTrendingSeries(String apikey , String language){
        final MutableLiveData<SerieResponse> data = new  MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getTrendingSeries(apikey,language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serieResponse ->
                        data.setValue(serieResponse)));
        return  data;
    }

 public LiveData<SerieResponse> getAiringSeries(String apikey , String language){
        final MutableLiveData<SerieResponse> data = new  MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getAiringSeries(apikey,language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serieResponse ->
                        data.setValue(serieResponse)));
        return  data;
    }

   public LiveData<SerieResponse> getOnairSeries(String apikey , String language){
        final MutableLiveData<SerieResponse> data = new  MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getAiringSeries(apikey,language)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serieResponse ->
                        data.setValue(serieResponse)));
        return  data;
    }

    public LiveData<SerieResponse> getTopratedSeries(String apikey , String language, int page){
        final MutableLiveData<SerieResponse> data = new  MutableLiveData<>();

        compositeDisposable.add(tmbdInterface.getTopratedSeries(apikey,language,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serieResponse ->
                        data.setValue(serieResponse)));
        return  data;
    }

    public LiveData<SerieDetails> getSerieDetail(int seriesId , String apikey){
        final MutableLiveData<SerieDetails> data = new MutableLiveData<>();
        tmbdInterface.getSerieDetail(seriesId,apikey)
                .enqueue(new Callback<SerieDetails>()  {
                    @Override
                    public void onResponse(Call<SerieDetails> call, Response<SerieDetails> response) {

                        if(response.isSuccessful()){
                            data.setValue(response.body());
                            System.out.println(response.body().getName());
                        }
                        System.out.println("hata" + response.code());

                    }

                    @Override
                    public void onFailure(Call<SerieDetails> call, Throwable t) {
                        System.out.println("hata" + t);
                    }
                });
       return data;
    }

    public LiveData<CastResponse> getCast(int movieId , String apiKey){
        final MutableLiveData<CastResponse> data = new MutableLiveData<>();
        tmbdInterface.getSerieCast(movieId , apiKey)
                .enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<CastResponse> call, Throwable t) {

                    }
                });
        return data;
    }







}
