package com.example.ebm.modele;

public class CollecResponse {

    private CollectionDetail collection;

    public CollecResponse(CollecResponse collectionResponse) {
        this.collection = collectionResponse.collection;
    }

    public CollectionDetail getCollection() {
        return collection;
    }
}
