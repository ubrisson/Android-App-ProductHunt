package com.example.ebm;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.modele.CollectionsList;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsActivity extends BaseDrawerActivity {

    private String TAG = "CollectionsActivity";
    private CollectionsList collectionsList;

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
            recupererCollectionsList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Debut de region API
    public void recupererCollectionsList(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<CollectionsList> call = api.appelCollections(true);
        call.enqueue(new Callback<CollectionsList>() {
            @Override
            public void onResponse(Call<CollectionsList> call, Response<CollectionsList> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: succesful");
                    collectionsList = new CollectionsList(response.body());
                    Log.i(TAG, "onResponse: " + collectionsList.toString());

                } else {
                    Log.i(TAG, "onResponse: not that succesful");
                }
            }

            @Override
            public void onFailure(Call<CollectionsList> call, Throwable t) {
                Log.i(TAG, "onFailure: Collections call Failed" + t.getMessage());}
        });
    }
    //Fin de region API
}
