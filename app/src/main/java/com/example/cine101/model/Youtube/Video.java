package com.example.cine101.model.Youtube;

import java.util.ArrayList;

public class Video {
    private String kind;
    private String etag;
    private ArrayList<Items>items;
   // private ArrayList<PageInfo> PageInfo;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    /* public ArrayList<PageInfo> getPageInfo() {
        return PageInfo;
    }*/
}



