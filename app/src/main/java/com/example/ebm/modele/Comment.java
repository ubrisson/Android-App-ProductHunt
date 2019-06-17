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
}
