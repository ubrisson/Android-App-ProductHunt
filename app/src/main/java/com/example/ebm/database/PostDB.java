package com.example.ebm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ebm.modele.Post;

@Entity(tableName = "posts")
public class PostDB {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String subTitle;

    @ColumnInfo
    private int nbCom;

    @ColumnInfo
    private String imageUrl;

    @ColumnInfo
    private String postUrl;

    @ColumnInfo
    private String created_at;

    public PostDB() {
    }

    public PostDB(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.subTitle = post.getSubTitle();
        this.nbCom = post.getNbCom();
        this.imageUrl = post.getImageUrl();
        this.postUrl = post.getPostUrl();
        this.created_at = post.getDate();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getNbCom() {
        return nbCom;
    }

    public void setNbCom(int nbCom) {
        this.nbCom = nbCom;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getCreated_at() {
        return created_at.substring(0,10) + " " + created_at.substring(11,16);
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
