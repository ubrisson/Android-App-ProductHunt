package com.example.ebm.modele;

import com.example.ebm.database.PostDB;
import com.google.gson.annotations.SerializedName;

public class Post {

    private long id;

    @SerializedName("name")
    private String title;

    @SerializedName("tagline")
    private String subTitle;

    @SerializedName("comments_count")
    private int nbCom;

    private Thumbnail thumbnail;

    @SerializedName("redirect_url")
    private String postUrl;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getNbCom() {
        return nbCom;
    }

    public void setNbCom(int nbCom) {
        this.nbCom = nbCom;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageUrl() {
        return thumbnail.getImage_url();
    }

    public Post(PostDB post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.subTitle = post.getSubTitle();
        this.nbCom = post.getNbCom();
        this.thumbnail = new Thumbnail(post.getImageUrl());
        this.postUrl = post.getPostUrl();
    }
}
