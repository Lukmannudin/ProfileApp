package com.oleg.profileapp.list_friends;

import android.content.Intent;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.User;

import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsContract {
    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showListFriends(List<User> users);

        void showFriendDetailUI(List<User> users, User user, int index);

        void showMessage(String message);

        void callFriend(Intent intent);
    }

    interface Presenter extends BasePresenter {
        void loadListFriends(boolean forceUpdate);

        void addNewFriend();

        void openDetailFriendDetail(List<User> users, User requestedUser, int index);

        void onDeleteFriend(User user);

        void onCallFriend(User user);

        void onEditFriend(User user, List<User> users, int index);
    }
}
