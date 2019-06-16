package com.example.ebm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ebm.modele.Post;

public class MainActivity extends BaseDrawerActivity
        implements PostsFragment.OnListFragmentInteractionListener {

    private PostsFragment fragment;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.fragment_main, contentFrameLayout);

        fragment = (PostsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            fragment.recupererPostsList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListFragmentInteraction(Post post) {
        Log.i(TAG, "onListFragmentInteraction: clicked" + post.getTitle());
        Intent postIntent = new Intent(this,PostDetailActivity.class);
        postIntent.putExtra("postUrl", post.getPostUrl());
        startActivity(postIntent);
    }
}
