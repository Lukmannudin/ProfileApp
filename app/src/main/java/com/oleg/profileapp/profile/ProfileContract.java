package com.oleg.profileapp.profile;

import android.content.Context;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.Profile;
import com.oleg.profileapp.model.User;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

import java.util.List;


// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class ProfileContract {

    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showProfile(User profile);
    }

    interface Presenter extends BasePresenter {
        void start(String username);

        void loadProfile(Context context,boolean forceUpdate);
        void onEditNim(User user, String nim);
        void onEditUsername(User user, String username);
        void onEditClass(User user, String kelas);
        void onEditDescription(User user, String description);
    }
}
