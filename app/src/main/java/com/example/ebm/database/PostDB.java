package com.example.ebm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

}
