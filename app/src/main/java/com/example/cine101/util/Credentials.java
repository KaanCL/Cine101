package com.example.cine101.util;

import retrofit2.Retrofit;

public class Credentials {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static final String API_KEY = "a42d32093e92ce5fc7277b527e8734b7";

    public static final int page = 1;

    public static final String  language = "en-US";

    public static final String region = "US";

    public static final String time = "day";

    public static int MovieID = 0;

    public static void setMovieID(int movieId) {
        Credentials.MovieID = movieId;
    }

    public static String Query = "";

    public static void setQuery(String query) {
        Query = query;
    }
}
