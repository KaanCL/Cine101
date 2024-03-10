package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.model.SeasonDetails;
import com.example.cine101.model.SerieDetails;
import com.example.cine101.repository.SeriesRespository;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.SerieResponse;
import com.example.cine101.util.Credentials;

import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.Query;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.ID;
import static com.example.cine101.util.Credentials.seasonNumber;


public class SeriesViewModel extends AndroidViewModel {
    private SeriesRespository seriesRespository;
    private LiveData<SerieResponse> SeriesPopularLiveData ,
            SeriesTrendingLiveData , SeriesAiringLiveData ,
            SeriesOnairLiveData, seriesTopratedLiveData , serieSearchResult ;

    private LiveData<SerieDetails> serieDetailsLiveData;

    private  LiveData<CastResponse> castResponseLiveData;

    private LiveData<SeasonDetails> seasonDetailsLiveData;

    public SeriesViewModel(@NonNull Application application) {
        super(application);
        seriesRespository = new SeriesRespository();
        SeriesPopularLiveData  = seriesRespository.getPopularSeries(API_KEY,language,page);
        SeriesTrendingLiveData =  seriesRespository.getTrendingSeries(API_KEY,language);
        SeriesAiringLiveData   = seriesRespository.getAiringSeries(API_KEY,language);
        SeriesOnairLiveData    = seriesRespository.getOnairSeries(API_KEY,language);
        seriesTopratedLiveData = seriesRespository.getTopratedSeries(API_KEY,language,page);
        serieDetailsLiveData = seriesRespository.getSerieDetail( ID,API_KEY);
        castResponseLiveData = seriesRespository.getCast(ID,API_KEY);
        seasonDetailsLiveData = seriesRespository.getSeasonDetail(ID,seasonNumber,API_KEY,language);
        serieSearchResult = seriesRespository.getSerieSearchResult(API_KEY,Query);
    }

    public LiveData<SerieResponse> getSeriesPopularLiveData() {return SeriesPopularLiveData;}

    public LiveData<SerieResponse> getSeriesTrendingLiveData() {return SeriesTrendingLiveData;}

    public LiveData<SerieResponse> getSeriesAiringLiveData() {return SeriesAiringLiveData;}

    public LiveData<SerieResponse> getSeriesOnairLiveData() {return SeriesOnairLiveData;}

    public LiveData<SerieResponse> getSeriesTopratedLiveData() {return seriesTopratedLiveData;}

    public LiveData<SerieDetails> getSerieDetailsLiveData() {return serieDetailsLiveData;}

    public LiveData<CastResponse> getCastResponseLiveData() {return castResponseLiveData;}

    public LiveData<SeasonDetails> getSeasonDetailsLiveData() {return seasonDetailsLiveData;}

    public LiveData<SerieResponse> getSerieSearchResult() {return serieSearchResult;}
}
