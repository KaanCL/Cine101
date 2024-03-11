package com.example.cine101.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cine101.model.Tmdb.SeasonDetails;
import com.example.cine101.model.Tmdb.SerieDetails;
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
                            System.out.println(response.body().getId());

                        }
                    }

                    @Override
                    public void onFailure(Call<SerieDetails> call, Throwable t) {
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

   public LiveData<SeasonDetails> getSeasonDetail(int seriesId , int seasonId , String apikey , String language){
        final MutableLiveData<SeasonDetails> data = new MutableLiveData<>();

        tmbdInterface.getSeasonDetail(seriesId,seasonId,apikey,language)
                .enqueue(new Callback<SeasonDetails>() {
                    @Override
                    public void onResponse(Call<SeasonDetails> call, Response<SeasonDetails> response) {
                        if(response.isSuccessful()){
                            data.setValue(response.body());
                        }
                        System.out.println("hata:" + response.code());
                    }

                    @Override
                    public void onFailure(Call<SeasonDetails> call, Throwable t) {
                        System.out.println("hatas" + t);
                    }
                });


         return data;
    }

    public LiveData<SerieResponse> getSerieSearchResult(String apikey , String query){

        final MutableLiveData<SerieResponse> data = new MutableLiveData<>();
        tmbdInterface.getSearchSerie(apikey,query)
                .enqueue(new Callback<SerieResponse>() {
                    @Override
                    public void onResponse(Call<SerieResponse> call, Response<SerieResponse> response) {
                        if (response.isSuccessful() || response.code()==200){
                            data.setValue(response.body());
                        }
                        else {
                            System.out.println("SerieSearch hata: " + response.code());

                        }
                    }
                    @Override
                    public void onFailure(Call<SerieResponse> call, Throwable t) {
                        System.out.println("SerieSearch hata: " + t);
                    }
                });
        return  data;
    }

}
