package com.example.cine101.model.Youtube;

import java.util.ArrayList;

public class Snippet {

    private String publishedAt;
    private String channelId;
    public  String title;
    private String description;
    private Thumbnails Thumbnails;
    private String channelTitle;
    private String[] tags;
    private String categoryId;
    private String liveBroadcastContent;
    Localized Localized;
    private String defaultAudioLanguage;


    public String getPublishedAt() {
        return publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public com.example.cine101.model.Youtube.Localized getLocalized() {
        return Localized;
    }

    public com.example.cine101.model.Youtube.Thumbnails getThumbnails() {
        return Thumbnails;
    }

    public String getDefaultAudioLanguage() {
        return defaultAudioLanguage;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String[] getTags() {
        return tags;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }
}
