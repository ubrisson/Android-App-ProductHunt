package com.example.ebm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.ebm.modele.Post;

import java.util.Objects;

public class DetailCollectionActivity extends AppCompatActivity implements PostsFragment.OnListFragmentInteractionListener {

    private PostsFragment fragment;
    private String TAG = "DetailCollectionActivity";
    int idCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_collec_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idCollection = extras.getInt("idCollection",-1);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = PostsFragment.newInstance(idCollection);
        fragmentManager.beginTransaction()
                .add(R.id.frameLayout,fragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            fragment.recupererPosts();
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