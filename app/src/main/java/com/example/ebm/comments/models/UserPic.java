package com.example.ebm.comments.models;

import com.google.gson.annotations.SerializedName;

class UserPic {

    @SerializedName("original")
    private String image_url;

    public String getImage_url() {
        return image_url;
    }
}
