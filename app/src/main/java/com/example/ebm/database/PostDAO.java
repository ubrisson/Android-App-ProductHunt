package com.example.ebm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ebm.modele.Post;

import java.util.List;

@Dao
public interface PostDAO {
    @Query("Select * from posts")
    List<Post> getPostsList();

    @Insert
    void insertPost();

    @Update
    void updatePost();

    @Delete
    void deletePost();

}
