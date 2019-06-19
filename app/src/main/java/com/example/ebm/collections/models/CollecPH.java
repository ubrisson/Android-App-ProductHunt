package com.example.ebm.collections.models;

import androidx.annotation.NonNull;

public class CollecPH {

    private int id;
    private String name;
    private String collection_url;
    private String title;
    private String background_image_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollection_url() {
        return collection_url;
    }

    public void setCollection_url(String collection_url) {
        this.collection_url = collection_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground_image_url() {
        return background_image_url;
    }

    public void setBackground_image_url(String background_image_url) {
        this.background_image_url = background_image_url;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
