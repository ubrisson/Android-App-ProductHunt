package com.example.ebm.comments;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.R;
import com.example.ebm.comments.models.Comment;
import com.example.ebm.comments.models.Comments;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private long idPost;
    private String titlePost;
    private int idLastComment;
    private RecyclerView recyclerView;
    private CommentsAdapter adapter;
    private String TAG = "CommentsActivity";
    private SwipeRefreshLayout swipeContainer;
    private ArrayList<Comment> comments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPost = extras.getLong("idPost",-1);
            titlePost = extras.getString("titlePost","FastAPI");
        }

        getSupportActionBar().setTitle(titlePost);
        getSupportActionBar().setSubtitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar()

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(
                this.getResources().getDrawable(R.drawable.sk_line_divider, getTheme()));

        recyclerView = findViewById(R.id.comments_list);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommentsAdapter(comments);
        recyclerView.setAdapter(adapter);
        recupererComments();

        swipeContainer = findViewById(R.id.swipeContainerComments);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recupererNouveauxComments();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Debut de region API
    public void recupererComments(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<Comments> call = api.appelComments(idPost,"created_at");
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (response.isSuccessful()) {
                    comments.addAll(unnest(response.body().getComments()));
                    whatshappening();
                    idLastComment = comments.get(comments.size()-1).getId();
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
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

    public void recupererNouveauxComments(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<Comments> call = api.appelNewComments(idPost,idLastComment,"created_at");
        call.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (response.isSuccessful()) {
                    comments.addAll(unnest(response.body().getComments()));
                    idLastComment = comments.get(comments.size()-1).getId();
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
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

    private void whatshappening(){
        for (Comment c: comments) {
            Log.i(TAG,  c.toString());
        }

    }

    int depth = 0;
    private ArrayList<Comment> unnest(ArrayList<Comment> list){
        ArrayList<Comment> flatarray = new ArrayList<Comment>();
        for (Comment parent:list) {
            parent.setDepth(depth);
            flatarray.add(parent);
            if(!parent.getChild_comments().isEmpty()){
                depth += 1;
                flatarray.addAll(unnest(parent.getChild_comments()));
                depth -=1;
            }
        }
        return flatarray;
    }
}
