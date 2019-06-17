package com.example.ebm;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.collections_feature.CollectionsAdapter;
import com.example.ebm.modele.CollectionsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsActivity extends BaseDrawerActivity implements CollectionsAdapter.onClickCollecListener {

    private String TAG = "Collections";
    private CollectionsList collectionsList;
    private RecyclerView recyclerView;
    private CollectionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(TAG);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.collections_main, contentFrameLayout);


        recupererCollectionsList();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh) {
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
                    recyclerView = findViewById(R.id.recyclerView);
                    adapter = new CollectionsAdapter(collectionsList.getCollections(),CollectionsActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(CollectionsActivity.this,2));
                    recyclerView.addItemDecoration( new MarginItemDecoration(
                            (int) getResources().getDimension(R.dimen.default_padding) ));
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

    @Override
    public void clickCollec(int position) {
        Log.i(TAG, "clickCollec: Clicked");
        Intent detailCollecIntent = new Intent(this,DetailCollectionActivity.class);
        detailCollecIntent.putExtra("idCollection", collectionsList.getCollections().get(position).getId());
        startActivity(detailCollecIntent);
    }


    class MarginItemDecoration extends RecyclerView.ItemDecoration {

        private int spaceHeight;

        MarginItemDecoration(int spaceHeight) {
            this.spaceHeight = spaceHeight;
        }

        @SuppressWarnings("SuspiciousNameCombination")
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state){
            if (parent.getChildAdapterPosition(view) <= 1  ){
                outRect.top = spaceHeight;
            }
            if(parent.getChildAdapterPosition(view) % 2 == 1){
                outRect.right = spaceHeight;
            }
            outRect.bottom = spaceHeight;
            outRect.left = spaceHeight;
        }
    }

}
