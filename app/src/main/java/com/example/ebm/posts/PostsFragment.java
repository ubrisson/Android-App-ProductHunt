package com.example.ebm.posts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.R;
import com.example.ebm.comments.CommentsActivity;
import com.example.ebm.database.PostDB;
import com.example.ebm.database.PostsDatabase;
import com.example.ebm.posts.models.CollecResponse;
import com.example.ebm.posts.models.Post;
import com.example.ebm.posts.models.PostsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PostsFragment extends Fragment implements PostsAdapter.onClickPostListener{

    private PostsAdapter adapter;
    private static final String ARG_ID_COLLEC = "idCollec";
    private PostsDatabase database;
    private ArrayList<PostDB> postsList;
    private int mIdCollection = -1;
    private SwipeRefreshLayout swipeContainer;
    private String TAG = "PostsFragment";
    private OnListFragmentInteractionListener mListener;
    private ExecutorService dbExecutor = Executors.newSingleThreadExecutor();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostsFragment() {
    }

    public static PostsFragment newInstance(int idCollection) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_COLLEC, idCollection);
        fragment.setArguments(args);
        Log.i("fragment", "newInstance");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIdCollection = getArguments().getInt(ARG_ID_COLLEC);
        }

        postsList = new ArrayList<>();
        adapter = new PostsAdapter(postsList,PostsFragment.this);

        if (mIdCollection == -1) {
            database = PostsDatabase.getInstance(getContext());
            recupererPostsDB();
        }
        else{
            recupererCollectionPosts();
        }
        Log.i(TAG, "onCreate: idCollec " + mIdCollection);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.posts_list, container, false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(Objects.requireNonNull(getContext()), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(
                this.getResources().getDrawable(R.drawable.sk_line_divider, Objects.requireNonNull(getActivity()).getTheme()));

        RecyclerView recyclerView = rootview.findViewById(R.id.list);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeContainer = (SwipeRefreshLayout) rootview;
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recupererPostsAPI();
            }
        });
        swipeContainer.setRefreshing(true);
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(PostDB post);
    }

    //Debut de region API
    private void recupererPostsAPI(){
        if(mIdCollection == -1)
            recupererPostsList();
        else
            recupererCollectionPosts();
    }

    private void recupererCollectionPosts() {
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<CollecResponse> call = api.appelPostsCollec(mIdCollection);
        Log.i(TAG, "recupererCollectionPosts: " + call.request().url());
        call.enqueue(new Callback<CollecResponse>() {
            @Override
            public void onResponse(@NonNull Call<CollecResponse> call, @NonNull Response<CollecResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    ArrayList<Post> posts = (ArrayList<Post>) response.body().getCollection().getPosts();
                    if (postsList != null)
                        postsList.clear();
                    else
                        postsList = new ArrayList<>();
                    for (Post p : posts) {
                        postsList.add(new PostDB(p));
                    }
                    showPosts();
                    Log.i(TAG, "onResponse: succesful");
                } else {
                    Log.i(TAG, "onResponse: not that succesful");
                }
            }
            @Override
            public void onFailure(@NonNull Call<CollecResponse> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: Posts call Failed" + " "+ t.getMessage());}
        });
    }



    private void recupererPostsList(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<PostsList> call;
        call = api.appelPosts();
        Log.i(TAG, "recupererPostsList: " + call.request().url());
        call.enqueue(new Callback<PostsList>() {
            @Override
            public void onResponse(@NonNull Call<PostsList> call, @NonNull Response<PostsList> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    updateDBwithAPI(response.body().getPosts());
                    Log.i(TAG, "onResponse: succesful");
                } else {
                    Log.i(TAG, "onResponse: not that succesful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostsList> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: Posts call Failed" + t.getMessage());}
        });
    }
    //Fin de region API

    @Override
    public void clickPost(int position) {
        Log.i(TAG, "clickPost: Clicked post");
        mListener.onListFragmentInteraction(postsList.get(position));
    }

    @Override
    public void clickComm(int position) {
        Log.i(TAG, "clickComm: Clicked comm");
        Intent intent = new Intent(getActivity(), CommentsActivity.class);
        intent.putExtra("idPost",postsList.get(position).getId());
        intent.putExtra("titlePost",postsList.get(position).getTitle());
        startActivity(intent);
    }

    //Start region database
    private void recupererPostsDB() {
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                postsList.addAll(database.postDAO().getPostsList());
                showPosts();
            }
        });

    }

    private void updateDBwithAPI(final List<Post> posts){
        dbExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (Post p : posts) {
                    database.postDAO().insertPost(new PostDB(p));
                }
                postsList.clear();
                postsList.addAll(database.postDAO().getPostsList());
                showPosts();
            }
        });

    }
    //End region database

    private void showPosts(){
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.show(postsList);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    public void updatePosts(){
        swipeContainer.setRefreshing(true);
        recupererPostsAPI();
    }

}
