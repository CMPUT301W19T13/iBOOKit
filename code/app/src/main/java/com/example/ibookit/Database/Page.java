package com.example.ibookit.Database;

import android.content.ClipData;

import java.util.List;

public class Page {
    private String title;
    private String link;
    private String description;
    private String language;
    private List<ClipData.Item> items;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public List<ClipData.Item> getItems() {
        return items;
    }
}
