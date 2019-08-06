package com.oleg.profileapp.repo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oleg.profileapp.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
