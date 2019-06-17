package com.example.ebm;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.modele.Comment;
import com.example.ebm.modele.Comments;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private long idPost;
    private String titlePost;
    private ArrayList<Comment> comments = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommentsAdapter adapter;
    private String TAG = "CommentsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPost = extras.getLong("idPost",-1);
            titlePost = extras.getString("titlePost","Ruby on Rails");
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(titlePost);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(
                this.getResources().getDrawable(R.drawable.sk_line_divider, getTheme()));

        recyclerView = findViewById(R.id.comments_list);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentsAdapter(comments);
        recyclerView.setAdapter(adapter);
        recupererComments();
    }

    //Debut de region API
    public void recupererComments(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<Comments> call = api.appelComments(idPost,"created_at");
        Log.i(TAG, "recupererComments: " + idPost);
        Log.i(TAG, "recupererComments: " + call.request());
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (response.isSuccessful()) {
                    comments = new Comments(response.body()).getComments();
                    adapter = new CommentsAdapter(comments);
                    recyclerView.setAdapter(adapter);
                    Log.i(TAG, "onResponse: " + comments.get(0).getId());
                    Log.i(TAG, "onResponse: succesful");

                } else {
                    Log.i(TAG, "onResponse: not that succesful");
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                Log.i(TAG, "onFailure: Collections call Failed" + t.getMessage());}
        });
    }
    //Fin de region API
}
