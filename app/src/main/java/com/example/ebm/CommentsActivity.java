package com.example.ebm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class CommentsActivity extends AppCompatActivity {

    private String TAG = "CollectionDetailActivity";
    int idPost;
    String titlePost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPost = extras.getInt("idPost",-1);
            titlePost = extras.getString("titlePost","Ruby on Rails");
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(titlePost);
        setSupportActionBar(toolbar);



        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }
}
