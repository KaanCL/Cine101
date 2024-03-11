package com.example.cine101.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cine101.repository.PeopleRespository;
import com.example.cine101.responses.PeopleResponse;

import static com.example.cine101.util.Credentials.API_KEY_TMDB;
import static com.example.cine101.util.Credentials.language;

public class PeopleViewModel extends AndroidViewModel {

    private PeopleRespository peopleRespository;
    private LiveData<PeopleResponse> PeoplePopularLiveData , PeopleTrendingLiveData;

    public PeopleViewModel(@NonNull Application application) {
        super(application);
        peopleRespository = new PeopleRespository();
        PeoplePopularLiveData = peopleRespository.getPopularPeople(API_KEY_TMDB,language);
        PeopleTrendingLiveData = peopleRespository.getTrendingPeople(API_KEY_TMDB,language);
    }

    public LiveData<PeopleResponse> getPeoplePopularLiveData() {return PeoplePopularLiveData;
    }

    public LiveData<PeopleResponse> getPeopleTrendingLiveData() {return PeopleTrendingLiveData;
    }
}
