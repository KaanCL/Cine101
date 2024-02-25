package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.repository.SeriesRespository;
import com.example.cine101.responses.SerieResponse;
import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.MovieID;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;
import static  com.example.cine101.util.Credentials.Query;


public class SeriesViewModel extends AndroidViewModel {
    private SeriesRespository seriesRespository;
    private LiveData<SerieResponse> SeriesPopularLiveData ,
            SeriesTrendingLiveData , SeriesAiringLiveData ,
            SeriesOnairLiveData, seriesTopratedLiveData;

    public SeriesViewModel(@NonNull Application application) {
        super(application);
        seriesRespository = new SeriesRespository();
        SeriesPopularLiveData  = seriesRespository.getPopularSeries(API_KEY,language,page);
        SeriesTrendingLiveData =  seriesRespository.getTrendingSeries(API_KEY,language);
        SeriesAiringLiveData   = seriesRespository.getAiringSeries(API_KEY,language);
        SeriesOnairLiveData    = seriesRespository.getOnairSeries(API_KEY,language);
        seriesTopratedLiveData = seriesRespository.getTopratedSeries(API_KEY,language,page);
    }

    public LiveData<SerieResponse> getSeriesPopularLiveData() {return SeriesPopularLiveData;
    }

    public LiveData<SerieResponse> getSeriesTrendingLiveData() {return SeriesTrendingLiveData;
    }

    public LiveData<SerieResponse> getSeriesAiringLiveData() {return SeriesAiringLiveData;
    }

    public LiveData<SerieResponse> getSeriesOnairLiveData() {return SeriesOnairLiveData;
    }

    public LiveData<SerieResponse> getSeriesTopratedLiveData() {return seriesTopratedLiveData;
    }
}
