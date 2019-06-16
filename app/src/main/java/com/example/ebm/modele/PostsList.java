package com.example.ebm.modele;

import androidx.annotation.NonNull;

import java.util.List;

public class PostsList {
    private List<Post> posts;

    public PostsList(PostsList postsList) {
        this.posts = postsList.posts;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sortie = new StringBuilder();
        for (Post x: posts) {
            sortie.append(" ").append(x.toString());
        }
        return sortie.toString();
    }

    public List<Post> getPosts() {
        return posts;
    }
}
