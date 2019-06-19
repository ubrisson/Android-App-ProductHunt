package com.example.ebm.API;

import com.example.ebm.posts.models.CollecResponse;
import com.example.ebm.collections.models.CollectionsList;
import com.example.ebm.comments.models.Comments;
import com.example.ebm.posts.models.PostsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIInterface {

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
    "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("collections")
    Call<CollectionsList> appelCollections(@Query("search[featured]") boolean featured);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
            "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("posts/all")
    Call<PostsList> appelPosts();

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
            "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("collections/{idCollection}")
    Call<CollecResponse> appelPostsCollec(@Path("idCollection") int idCollection);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
            "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("posts/{idPost}/comments")
    Call<Comments> appelComments(@Path("idPost") long idPost,
                                 @Query("sort_by") String created_at);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Host: api.producthunt.com",
            "Authorization: Bearer 46a03e1c32ea881c8afb39e59aa17c936ff4205a8ed418f525294b2b45b56abb"})
    @GET("posts/{idPost}/comments")
    Call<Comments> appelNewComments(@Path("idPost") long idPost,
                                    @Query("newer") long idLastComment,
                                    @Query("sort_by") String created_at);
}
