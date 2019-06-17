package com.example.ebm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDAO {
    @Query("Select * from posts")
    List<PostDB> getPostsList();

    @Insert
    void insertPost(PostDB postDB);

    @Insert
    void insertAll(List<PostDB> listPostDB);

    @Update
    void updatePost(PostDB postDB);

    @Delete
    void deletePost(PostDB postDB);

}
