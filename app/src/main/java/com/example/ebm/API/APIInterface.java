package com.example.ebm.API;

import com.example.ebm.modele.CollectionsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface APIInterface {

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
    "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("collections")
    Call<CollectionsList> appelCollections(@Query("search[featured]") boolean featured);

}
