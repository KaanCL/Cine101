package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.repository.PeopleRespository;
import com.example.cine101.responses.CastResponse;
import com.example.cine101.responses.PeopleResponse;
import com.example.cine101.repository.SeriesRespository;
import com.example.cine101.responses.SerieResponse;
import static com.example.cine101.util.Credentials.API_KEY;
import static com.example.cine101.util.Credentials.BASE_URL;
import static com.example.cine101.util.Credentials.MovieID;
import static com.example.cine101.util.Credentials.language;
import static com.example.cine101.util.Credentials.page;
import static com.example.cine101.util.Credentials.region;
import static  com.example.cine101.util.Credentials.Query;

public class PeopleViewModel extends AndroidViewModel {

    private PeopleRespository peopleRespository;
    private LiveData<PeopleResponse> PeoplePopularLiveData , PeopleTrendingLiveData;

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        peopleRespository = new PeopleRespository();
        PeoplePopularLiveData = peopleRespository.getPopularPeople(API_KEY,language);
        PeopleTrendingLiveData = peopleRespository.getTrendingPeople(API_KEY,language);
    }

    public LiveData<PeopleResponse> getPeoplePopularLiveData() {return PeoplePopularLiveData;
    }

    public LiveData<PeopleResponse> getPeopleTrendingLiveData() {return PeopleTrendingLiveData;
    }
}
