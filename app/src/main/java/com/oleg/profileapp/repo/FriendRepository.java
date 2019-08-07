package com.oleg.profileapp.repo;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import androidx.room.Room;

import com.oleg.profileapp.model.Friend;
import com.oleg.profileapp.repo.dao.ProfileDatabase;

import java.util.List;
// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class FriendRepository {
    private String DB_NAME = "db_profileApp";
    private ProfileDatabase database;
    private Context context;

    private FriendRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, ProfileDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private static FriendRepository INSTANCE = null;

    public static FriendRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FriendRepository(context);
        }

        return INSTANCE;
    }

    public List<Friend> getFriends(int id) {
        return database.friendDao().getFriends(id);
    }

    public boolean createFriend(Friend friend) {
        return database.friendDao().createFriend(friend) >= 0;
    }

    public boolean deleteFriend(Friend friend) {
        boolean status = true;
        try {
            database.friendDao().delete(friend);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
        }

        return status;
    }

    public boolean updateFriend(Friend friend) {
        boolean status = true;
        try {
            database.friendDao().update(friend);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

}
