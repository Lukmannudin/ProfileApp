package com.oleg.profileapp.repo;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.room.Room;

import com.oleg.profileapp.model.Profile;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.dao.ProfileDatabase;

import java.util.ArrayList;
import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class UserRepository {

    private String DB_NAME = "db_profileApp";
    private ProfileDatabase database;
    private Context context;

    private UserRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, ProfileDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private static UserRepository INSTANCE = null;

    public static UserRepository getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new UserRepository(context);
        }

        return INSTANCE;

    }

    public User getUser(String username, String password){
        return database.userDao().getUser(username,password);
    }

    public User getUser(String username){
        return database.userDao().getUser(username);
    }

    public List<User> getUsers() {
        return database.userDao().getUsers();
    }

    public boolean createUser(User user) {
        return database.userDao().createUser(user) >= 0;
    }

    public boolean deleteUser(User user) {
        boolean status = true;
        try {
            database.userDao().delete(user);
        } catch (SQLiteException e){
            e.printStackTrace();
            status = false;
        }

        return status;
    }


    public boolean updateUser(User user){
        boolean status = true;
        try {
            database.userDao().updateContact(
                    user.getNim(),
                    user.getUsername(),
                    user.getKelas(),
                    user.getDescription(),
                    user.getTelepon(),
                    user.getEmail(),
                    user.getInstagram(),
                    user.getTwitter(),
                    user.getFacebook()
                    );
        } catch (SQLiteException e){
            e.printStackTrace();
            status =false;
        }
        return status;
    }



    static String[] profileData = new String[]{
            "",
            "10116347",
            "Lukmannudin",
            "IF - 8",
            "Learn From Yesterday, Life for Today, Hope for Tomorrow "
    };

    static String[] contactData = new String[]{
            "",
            "",
            "",
            "08813095231",
            "laodelukmannudinpriatna@gmail.com",
            "lord_lukman19",
            "lukmannudinpriatna",
            "Lukmannudin Priatna"

    };

    static String[][] friendsData = new String[][]{
            {
                    "10116340",
                    "Ibrahimovic Azkaban",
                    "IF - 12",
                    "08911231232",
                    "ibraazkaban@gmail.com",
                    "ibrazzz",
                    "ibrazinov",
                    "Ibrahim Azka"
            },
            {
                    "10116329",
                    "Zaenal Arif",
                    "IF - 7",
                    "0812334234",
                    "zaenalarif@gmail.com",
                    "zaenalarifff",
                    "babangzaenal",
                    "Zae Arif"
            },
            {
                    "10116210",
                    "Maman Abdruhaman",
                    "IF - 1",
                    "08231242342",
                    "maman@gmail.com",
                    "mamankoman",
                    "mamamamaman",
                    "Maman Kuu"
            }
    };

    public static ArrayList<Profile> getProfileData() {
        Profile p = null;

        ArrayList<Profile> list = new ArrayList<>();

        p = new Profile();
        p.setFoto(profileData[0]);
        p.setNim(profileData[1]);
        p.setNama(profileData[2]);
        p.setKelas(profileData[3]);
        p.setDeskripsi(profileData[4]);
        list.add(p);

        return list;
    }

    public static ArrayList<User> getContactData() {
        User user = null;

        ArrayList<User> list = new ArrayList<>();

        user = new User();
        user.setTelepon(contactData[3]);
        user.setEmail(contactData[4]);
        user.setTwitter(contactData[5]);
        user.setInstagram(contactData[6]);
        user.setFacebook(contactData[7]);
        list.add(user);

        return list;
    }

    public static ArrayList<User> getFriendsData() {
        User user = null;

        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < friendsData.length; i++) {
            user = new User();
            user.setNim(friendsData[i][0]);
            user.setUsername(friendsData[i][1]);
            user.setKelas(friendsData[i][2]);
            user.setTelepon(friendsData[i][3]);
            user.setEmail(friendsData[i][4]);
            user.setTwitter(friendsData[i][5]);
            user.setInstagram(friendsData[i][6]);
            user.setFacebook(friendsData[i][7]);
            list.add(user);
        }
        return list;
    }


}
