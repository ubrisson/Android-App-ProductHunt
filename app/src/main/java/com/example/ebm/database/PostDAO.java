package com.example.ebm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDAO {
    @Query("Select * from posts")
    List<PostDB> getPostsList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(PostDB... postsDB);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(PostDB postDB);

    @Update
    void updatePost(PostDB postDB);

    @Delete
    void deletePost(PostDB postDB);

}
