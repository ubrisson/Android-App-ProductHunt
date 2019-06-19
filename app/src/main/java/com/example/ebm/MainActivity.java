package com.example.ebm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ebm.database.PostDB;

import java.util.Objects;

public class MainActivity extends BaseDrawerActivity
        implements PostsFragment.OnListFragmentInteractionListener {

    private PostsFragment fragment;
    private String TAG = "Posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle(TAG);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.fragment_main, contentFrameLayout);

        fragment = (PostsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            fragment.updatePosts();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListFragmentInteraction(PostDB post) {
        Log.i(TAG, "onListFragmentInteraction: clicked" + post.getTitle());
        Intent postIntent = new Intent(this,PostDetailActivity.class);
        postIntent.putExtra("postUrl", post.getPostUrl());
        startActivity(postIntent);
    }
}