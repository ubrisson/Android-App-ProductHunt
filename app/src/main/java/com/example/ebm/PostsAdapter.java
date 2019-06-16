package com.example.ebm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.modele.Post;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    /* La liste à afficher */
    private List<Post> lesPosts;
    /* Écouteur d'évènement */
    private PostsAdapter.onClickPostListener listener;

    public PostsAdapter() {
    }

    public PostsAdapter(List<Post> lesPosts, PostsAdapter.onClickPostListener clickPostListener) {
        this.lesPosts = lesPosts;
        this.listener = clickPostListener;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PostsAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        holder.bind(lesPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return lesPosts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.post_header;
        } else {
            return R.layout.post;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView subtitle;
        ImageView thumbnail;
        TextView nbCom;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            title.setOnClickListener(this);
            subtitle = itemView.findViewById(R.id.subtitle);
            subtitle.setOnClickListener(this);
            nbCom = itemView.findViewById(R.id.nbCommentaires);
            nbCom.setOnClickListener(this);
            thumbnail = itemView.findViewById(R.id.bg_image);
            thumbnail.setOnClickListener(this);
        }

        void bind(Post post) {
            title.setText(post.getTitle());
            subtitle.setText(post.getSubTitle());
            nbCom.setText(String.format(Locale.FRANCE,"%d commentaires", post.getNbCom()));
            String url = post.getImageUrl();
            Picasso.get().load(url).into(thumbnail);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.nbCommentaires)
                listener.clickComm(getAdapterPosition());
            else
                listener.clickPost(getAdapterPosition());
        }
    }


    /**
     * Interface permettant de gérer le clic sur un élément
     */
    public interface onClickPostListener{
        void clickPost(int position);
        void clickComm(int position);
    }
}
