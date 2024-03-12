package com.example.cine101.util;

public class Credentials {

    public static final String BASE_URL_TMBD = "https://api.themoviedb.org/3/";

    public static final String BASE_URL_YOUTUBE ="https://www.googleapis.com/youtube/v3/";

    public static final String API_KEY_TMDB = "a42d32093e92ce5fc7277b527e8734b7";

    public static final String API_KEY_YOUTUBE="AIzaSyAr8u6IkvVJRO_AJkhffuOJ9nkVjS6BzjM";

    public static final String part ="Snippet";

    public static String Video_Id = "";

    public static final int page = 1;

    public static final String  language = "en-US";

    public static final String region = "US";

    public static final String time = "day";

    public static  int ID = 0;

    public static  int seasonNumber=0;

    public static String brand ="";

     public static void setID(int ID) {
        Credentials.ID = ID;
    }

    public static int getID() {
        return ID;
    }

    public static String Query = "";

    public static String getBrand() {
        return brand;
    }

    public static void setBrand(String brand) {
        Credentials.brand = brand;
    }

    public static void setQuery(String query) {
        Query = query;
    }

    public static void setSeasonNumber(double seasonNumber) {
        Credentials.seasonNumber = (int) seasonNumber;
    }

    public static String getVideo_Id() {
        return Video_Id;
    }

    public static void setVideo_Id(String video_Id) {
        Video_Id = video_Id;
    }

    public static int getSeasonNumber() {
        return seasonNumber;
    }


}
