package com.example.ebm.modele;

import java.util.ArrayList;

public class Comments {

    private ArrayList<Comment> comments;

    public Comments(Comments comments) {
        this.comments = comments.comments;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
