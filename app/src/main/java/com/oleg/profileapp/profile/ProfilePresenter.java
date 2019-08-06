package com.oleg.profileapp.profile;

import android.content.Context;

import com.oleg.profileapp.model.Profile;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.Repository;
import com.oleg.profileapp.util.Preferences;

import java.util.ArrayList;
import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.View mProfileView;

    private boolean mFirstLoad = true;

    private User repository = new User();
    private Context context;

    ProfilePresenter(ProfileContract.View mProfileView) {

        this.mProfileView = mProfileView;
        this.mProfileView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void loadProfile(Context context,boolean forceUpdate) {
        loadProfile(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
        this.context = context;
    }

    private void loadProfile(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mProfileView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            Preferences preferences = Preferences.getInstance(context);
            repository = preferences.getUserLogin();
        }

        if (showLoadingUI) {
            mProfileView.setLoadingIndicator(false);
        }

        processProfile(repository);
    }

    private void processProfile(User profiles) {
        mProfileView.showProfile(profiles);
    }
}
