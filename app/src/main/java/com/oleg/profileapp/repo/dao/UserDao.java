package com.oleg.profileapp.repo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.oleg.profileapp.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User getUser(String username, String password);

    @Insert
    long createUser(User user);

    @Delete
    void delete(User user);

}
