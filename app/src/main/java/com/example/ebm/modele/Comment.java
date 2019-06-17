package com.example.ebm.modele;

import java.util.ArrayList;

public class Comment {

    private int id;

    private String body;

    private int parent_comment_id;

    private int child_comments_count;

    private int post_id;

    private User user;

    private ArrayList<Comment> child_comments;


}
