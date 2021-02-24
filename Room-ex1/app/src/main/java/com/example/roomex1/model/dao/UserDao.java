package com.example.roomex1.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomex1.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> findAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE username LIKE :keyword")
    User findByName(String keyword);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertOne(User user);

    @Delete
    void delete(User user);
}
