package com.example.ebm.posts.models;

public class CollecResponse {

    private CollectionDetail collection;

    public CollecResponse(CollecResponse collectionResponse) {
        this.collection = collectionResponse.collection;
    }

    public CollectionDetail getCollection() {
        return collection;
    }
}
