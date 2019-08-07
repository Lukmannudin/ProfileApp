package com.oleg.profileapp.repo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oleg.profileapp.model.User;

import java.util.List;
// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User getUser(String username, String password);

    @Query("SELECT * FROM user WHERE username = :username")
    User getUser(String username);

    @Insert
    long createUser(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User... user);
}
