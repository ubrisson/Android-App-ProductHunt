package com.example.ebm.modele;

import androidx.annotation.NonNull;

import java.util.List;

public class CollectionDetail {

    private int id;
    private String name;
    private String collection_url;
    private String title;
    private String background_image_url;
    private List<Post> posts;

    public CollectionDetail(CollectionDetail collectionDetail) {
        this.id = collectionDetail.id;
        this.name = collectionDetail.name;
        this.title = collectionDetail.title;
        this.collection_url = collectionDetail.collection_url;
        this.background_image_url = collectionDetail.background_image_url;
        this.posts = collectionDetail.posts;
    }

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
