package com.example.ebm;

import android.os.Bundle;

import com.example.ebm.dummy.DummyContent;
import com.example.ebm.modele.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.FrameLayout;

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
    }
}
