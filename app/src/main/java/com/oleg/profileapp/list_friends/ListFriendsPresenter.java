package com.oleg.profileapp.list_friends;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.FrameLayout;

import com.oleg.profileapp.model.Friend;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.FriendRepository;
import com.oleg.profileapp.repo.UserRepository;
import com.oleg.profileapp.util.Preferences;

import java.util.ArrayList;
import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsPresenter implements ListFriendsContract.Presenter {

    private final ListFriendsContract.View mView;

    private boolean mFirstLoad = true;

    private List<Friend> repository = new ArrayList<>();

    private FriendRepository db;

    private Context context;

    ListFriendsPresenter(FrameLayout frameLayout, ListFriendsContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start(Context context) {
        this.context = context;
        db = FriendRepository.getInstance(context);
        start();
    }

    @Override
    public void start() {
        loadListFriends(false);
    }

    @Override
    public void loadListFriends(boolean forceUpdate) {
        loadListFriends(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadListFriends(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mView.setLoadingIndicator(true);
        }

        Preferences preferences = Preferences.getInstance(context);
        User currentUser = preferences.getUserLogin();

        if (forceUpdate) {
            repository = db.getFriends(currentUser.getId());
        }


        if (showLoadingUI) {
            mView.setLoadingIndicator(false);
        }

        processListFriends(repository);

    }

    private void processListFriends(List<Friend> friends) {
        mView.showListFriends(friends);
    }


    @Override
    public void addNewFriend(Friend friend) {
        if (db.createFriend(friend)) {
            loadListFriends(true);
            mView.showMessageSuccess(friend, "Friend added successfully");
        } else {
            mView.showMessageFailed("Add friend failed");
        }
    }

    @Override
    public void openDetailFriendDetail(List<Friend> friends, Friend requestedFriend, int index) {
        mView.showFriendDetailUI(friends, requestedFriend, index);
    }

    @Override
    public void onDeleteFriend(final Friend friend) {
        if (db.deleteFriend(friend)) {
            repository.remove(friend);
            mView.showListFriends(repository);
            mView.showMessage("User successfully deleted");
        } else {
            mView.showMessageFailed("User failed to delete");
        }
    }

    @Override
    public void onEditFriend(Friend friend) {
        if (db.updateFriend(friend)) {
            loadListFriends(true);
            mView.showMessage("Friend successfully updated");
        } else {
            mView.showMessageFailed("Friend failed to update");
        }
    }

    @Override
    public void onCallFriend(Friend friend) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + friend.getTelepon()));
        mView.callFriend(callIntent);
    }
}
