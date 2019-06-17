package com.example.ebm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = PostDB.class, version = 1 )
public abstract class PostsDatabase extends RoomDatabase {
    private static final String DB_name = "posts_db";
    private static PostsDatabase instance;

    public static synchronized PostsDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostsDatabase.class, DB_name)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public PostDAO postDAO;
}
