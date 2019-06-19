package com.example.ebm.comments.models;

import java.util.ArrayList;

public class Comment {

    private int id;

    private String body;

    private String created_at;

    private int parent_comment_id;

    private int child_comments_count;

    private int post_id;

    private User user;

    private ArrayList<Comment> child_comments;

    private int depth = 0;


    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getParent_comment_id() {
        return parent_comment_id;
    }

    public int getChild_comments_count() {
        return child_comments_count;
    }

    public int getPost_id() {
        return post_id;
    }


    public ArrayList<Comment> getChild_comments() {
        return child_comments;
    }

    public int getUserId() {
        return user.getId();
    }

    public String getUserNameTag() {
        return user.getName() + " - " + user.getTwitter_username();
    }

    public String getUserHeadline() {
        return user.getHeadline();
    }

    public String getUserPic() {
        return user.getImage_url();
    }

    public String getCreated_at() {
        return created_at.substring(0,10) + " " + created_at.substring(11,16);
    }

    @Override
    public String toString() {
        return "{ id :" + id +
                ", date: " + created_at +
                ", depth: " + depth +
                ", parent " + parent_comment_id +
                ", nb children " + child_comments_count +
                '}';
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
