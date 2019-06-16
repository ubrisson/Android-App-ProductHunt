package com.example.ebm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ebm.API.APIClient;
import com.example.ebm.API.APIInterface;
import com.example.ebm.modele.Post;
import com.example.ebm.modele.PostsList;

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

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private String TAG = "PostsFragment";
    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private DividerItemDecoration dividerItemDecoration;
    private PostsList postsList;
    private View rootview;
    private SwipeRefreshLayout swipeContainer;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostsFragment() {
    }

    public static PostsFragment newInstance(int columnCount) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.posts_list, container, false);

        dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(
                this.getResources().getDrawable(R.drawable.sk_line_divider,getActivity().getTheme()));

        swipeContainer = (SwipeRefreshLayout) rootview;
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recupererPostsList();
            }
        });


        recyclerView = rootview.findViewById(R.id.list);
        recupererPostsList(); //TODO Get Posts from databse here
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(dividerItemDecoration);

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Post post);
    }

    //Debut de region API
    public void recupererPostsList(){
        APIInterface api = APIClient.createService(APIInterface.class);
        Call<PostsList> call = api.appelPosts();
        call.enqueue(new Callback<PostsList>() {
            @Override
            public void onResponse(Call<PostsList> call, Response<PostsList> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: succesful");
                    postsList = new PostsList(response.body());
                    Log.i(TAG, "onResponse: " + postsList.toString());
                    adapter = new PostsAdapter(postsList.getPosts(),PostsFragment.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } else {
                    Log.i(TAG, "onResponse: not that succesful");
                }
            }

            @Override
            public void onFailure(Call<PostsList> call, Throwable t) {
                Log.i(TAG, "onFailure: Posts call Failed" + t.getMessage());}
        });
    }
    //Fin de region API

    @Override
    public void clickPost(int position) {
        Log.i(TAG, "clickPost: Clicked post");
        mListener.onListFragmentInteraction(postsList.getPosts().get(position));
        //TODO Lancer écran liste de commentaires
    }

    @Override
    public void clickComm(int position) {
        Log.i(TAG, "clickComm: Clicked comm");
        //TODO lancer écran liste de commentaires
    }
}
