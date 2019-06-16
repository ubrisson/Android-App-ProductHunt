package com.example.ebm;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class CollectionsActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.collections_main, contentFrameLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            //TODO Refresh collections
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
