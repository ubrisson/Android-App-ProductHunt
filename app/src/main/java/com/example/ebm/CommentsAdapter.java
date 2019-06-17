package com.example.ebm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebm.modele.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ItemViewHolder> {
    /* La liste à afficher */
    private List<Comment> lesComments;

    public CommentsAdapter(List<Comment> lesComments) {
        this.lesComments = lesComments;
    }

    @NonNull
    @Override
    public CommentsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsAdapter.ItemViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ItemViewHolder holder, int position) {
        holder.bind(lesComments.get(position));
    }

    @Override
    public int getItemCount() {
        return lesComments.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView name_tag;
        TextView headline;
        ImageView userPic;
        TextView body;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tag = itemView.findViewById(R.id.username_twitter_tag);
            headline = itemView.findViewById(R.id.headline);
            userPic = itemView.findViewById(R.id.user_image);
            body = itemView.findViewById(R.id.body);
        }

        void bind(Comment comment) {
            name_tag.setText(comment.getUserNameTag());
            headline.setText(comment.getUserHeadline());
            body.setText(comment.getBody());
            String url = comment.getUserPic();
            Picasso.get().load(url).fit().into(userPic);
        }

    }
}
