package com.oleg.profileapp.repo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oleg.profileapp.model.Friend;
import com.oleg.profileapp.model.User;

// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

@Database(entities = {User.class, Friend.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FriendDao friendDao();
}
