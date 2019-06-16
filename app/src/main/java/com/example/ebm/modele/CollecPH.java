package com.example.ebm.modele;

import androidx.annotation.NonNull;

public class CollecPH {

    private int id;
    private String name;
    private String collection_url;
    private String title;
    private String background_image_url;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
