package com.oleg.profileapp.list_friends;

import android.content.Intent;
import android.net.Uri;
import android.widget.FrameLayout;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.Repository;

import java.util.ArrayList;
import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsPresenter implements ListFriendsContract.Presenter {

    private final ListFriendsContract.View mView;

    private final FrameLayout frameLayout;

    private boolean mFirstLoad = true;

    private ArrayList<User> repository = new ArrayList<>();


    ListFriendsPresenter(FrameLayout frameLayout,ListFriendsContract.View mView) {
        this.frameLayout = frameLayout;
        this.mView = mView;
        this.mView.setPresenter(this);
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

        if (forceUpdate) {
            repository.clear();
            repository.addAll(Repository.getFriendsData());
        }

        if (showLoadingUI) {
            mView.setLoadingIndicator(false);
        }

        processListFriends(repository);

    }

    private void processListFriends(List<User> users) {
        mView.showListFriends(users);
    }


    @Override
    public void addNewFriend() {

    }

    @Override
    public void openDetailFriendDetail(List<User> users, User requestedUser, int index) {
        mView.showFriendDetailUI(users, requestedUser, index);
    }

    @Override
    public void onDeleteFriend(final User user) {
        repository.remove(user);
        mView.showListFriends(repository);
        mView.showMessage("Data Berhasil Dihapus");
    }

    @Override
    public void onEditFriend(User user, List<User> users, int index) {
        users.set(index, user);
        mView.showListFriends(users);
    }

    @Override
    public void onCallFriend(User user) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+ user.getTelepon()));
        mView.callFriend(callIntent);
    }
}
