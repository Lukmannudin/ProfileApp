package com.oleg.profileapp.list_friends.AddFriend;

import android.content.Context;

import com.oleg.profileapp.model.Friend;
import com.oleg.profileapp.repo.FriendRepository;
// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class AddFriendPresenter implements AddFriendContract.Presenter {
    private FriendRepository db;
    Context context;
    private AddFriendContract.View mView;


    public AddFriendPresenter(AddFriendContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        db = FriendRepository.getInstance(context);
    }

    @Override
    public void addFriend(Friend friend) {
        mView.setLoadingIndicator(true);
        if (db.createFriend(friend)) {
            mView.onSuccessAdd();
        } else {
            mView.onFailedAdd();
        }
        mView.setLoadingIndicator(false);
    }

    @Override
    public void start(Context context) {
        this.context = context;
        start();
    }

}
