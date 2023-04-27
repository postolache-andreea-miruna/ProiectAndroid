package com.example.proiectandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {

    //CRUD
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user ORDER BY id DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE name LIKE :search LIMIT 1")
    User findUserWithName(String search);
}
