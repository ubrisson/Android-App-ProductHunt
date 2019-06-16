package com.example.ebm.modele;


import androidx.annotation.NonNull;

import java.util.List;

public class CollectionsList {

    private List<CollecPH> collections;

    public CollectionsList(CollectionsList collectionsList) {
        this.collections = collectionsList.collections;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sortie = new StringBuilder();
        for (CollecPH x: collections) {
            sortie.append(" ").append(x.toString());
        }
        return sortie.toString();
    }
}
