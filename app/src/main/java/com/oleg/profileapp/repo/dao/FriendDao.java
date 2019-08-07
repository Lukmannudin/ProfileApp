package com.oleg.profileapp.repo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.oleg.profileapp.model.Friend;

import java.util.List;


// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
@Dao
public interface FriendDao {
    @Query("SELECT * FROM friend WHERE userid = :id")
    List<Friend> getFriends(int id);

    @Insert
    long createFriend(Friend friend);

    @Delete
    void delete(Friend friend);

    @Update
    void update(Friend... friends);
}
