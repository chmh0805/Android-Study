package com.example.roomex1.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomex1.model.User;
import com.example.roomex1.model.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
