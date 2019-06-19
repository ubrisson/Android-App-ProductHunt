package com.example.ebm.comments.models;

class User {

    private int id;

    private String name;

    private String headline;

    private String twitter_username;

    private UserPic image_url;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeadline() {
        return headline;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public String getImage_url() {
        return image_url.getImage_url();
    }
}
