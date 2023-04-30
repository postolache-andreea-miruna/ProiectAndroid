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

    @Query("SELECT EXISTS (SELECT * from user where password=:password AND email=:email)")
    Integer login(String email, String password);

    @Query("SELECT EXISTS (SELECT * from user where email=:email)")
    Integer existGoogleAccount(String email);

    @Query("SELECT id from user where email=:email")
    Integer idFromEmail(String email);
}
