package com.example.ebm.modele;

import com.google.gson.annotations.SerializedName;

public class Post {

    private long id;

    @SerializedName("name")
    private String title;

    @SerializedName("tagline")
    private String subTitle;

    @SerializedName("votes_count")
    private int nbCom;

    private Thumbnail thumbnail;

    @SerializedName("redirect_url")
    private String postUrl;



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

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getNbCom() {
        return nbCom;
    }

    public void setNbCom(int nbCom) {
        this.nbCom = nbCom;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageUrl() {
        return thumbnail.getImage_url();
    }

    /*
  public ContentValues toContentValues() {


    ContentValues contentValues = new ContentValues();
    contentValues.put(DataBaseContract.PostTable.ID_COLUMN, id);
    contentValues.put(DataBaseContract.PostTable.TITLE_COLUMN, title);
    contentValues.put(DataBaseContract.PostTable.SUBTITLE_COLUMN, subTitle);
    contentValues.put(DataBaseContract.PostTable.IMAGE_URL_COLUMN, imageUrl);
    contentValues.put(DataBaseContract.PostTable.POST_URL_COLUMN, postUrl);
    return contentValues;
  }
*/

}
