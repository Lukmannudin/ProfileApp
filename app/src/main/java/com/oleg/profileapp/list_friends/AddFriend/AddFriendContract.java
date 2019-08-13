package com.oleg.profileapp.list_friends.AddFriend;

import android.content.Context;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.Friend;
// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class AddFriendContract {
    interface Presenter extends BasePresenter {
        void addFriend(Friend friend);

        void start(Context context);
    }

    interface View extends BaseView<AddFriendContract.Presenter> {
        void onSuccessAdd();

        void onFailedAdd();

        void setLoadingIndicator(boolean active);
    }

}
