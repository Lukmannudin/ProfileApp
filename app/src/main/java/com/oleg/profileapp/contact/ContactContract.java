package com.oleg.profileapp.contact;

import android.content.Context;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.User;

import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public interface ContactContract {
    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showContact(User users);
    }

    interface Presenter extends BasePresenter {
        void loadContact(Context context,Boolean forceUpdate);
        void start(String username);

        void onEditTelepon(User user, String telepon);
        void onEditEmail(User user, String email);
        void onEditInstagram(User user, String instagram);
        void onEditTwitter(User user, String twitter);
        void onEditFacebook(User user, String facebook);
    }
}
