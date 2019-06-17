package com.example.ebm.modele;

import com.google.gson.annotations.SerializedName;

class UserPic {

    @SerializedName("40px")
    private String image_url;

    public String getImage_url() {
        return image_url;
    }
}
