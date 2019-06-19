package com.example.ebm.collections.models;


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

    public List<CollecPH> getCollections() {
        return collections;
    }
}
