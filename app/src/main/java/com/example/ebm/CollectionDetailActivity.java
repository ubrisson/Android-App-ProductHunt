package com.example.ebm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.ebm.database.PostDB;

import java.util.Objects;

public class CollectionDetailActivity extends AppCompatActivity implements PostsFragment.OnListFragmentInteractionListener {

    private PostsFragment fragment;
    private String TAG = "CollectionDetailActivity";
    int idCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_collec_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Collection's posts");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idCollection = extras.getInt("idCollection",-1);
        }


        Log.i(TAG, "onCreate: trying to add fragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            fragment = PostsFragment.newInstance(idCollection);
            fragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment,TAG)
                    .commit();
            Log.i(TAG, "onCreate: fragment added");
        }
        else{
            fragment = (PostsFragment) fragmentManager.findFragmentByTag(TAG);
            Log.i(TAG, "onCreate: fragment reused");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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