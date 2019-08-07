package com.oleg.profileapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
@Entity
public class Friend implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nim")
    private String nim;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "kelas")
    private String kelas;

    @ColumnInfo(name = "telepon")
    private String telepon;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "twitter")
    private String twitter;

    @ColumnInfo(name = "instagram")
    private String instagram;

    @ColumnInfo(name = "facebook")
    private String facebook;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "userid")
    private int userid;

    public Friend() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    protected Friend(Parcel in) {
        id = in.readInt();
        nim = in.readString();
        username = in.readString();
        kelas = in.readString();
        telepon = in.readString();
        email = in.readString();
        twitter = in.readString();
        instagram = in.readString();
        facebook = in.readString();
        password = in.readString();
        userid = in.readInt();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nim);
        parcel.writeString(username);
        parcel.writeString(kelas);
        parcel.writeString(telepon);
        parcel.writeString(email);
        parcel.writeString(twitter);
        parcel.writeString(instagram);
        parcel.writeString(facebook);
        parcel.writeString(password);
        parcel.writeInt(userid);
    }
}
