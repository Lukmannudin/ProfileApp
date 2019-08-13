package com.oleg.profileapp.profile;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.UserRepository;
import com.oleg.profileapp.util.Preferences;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ProfilePresenter implements ProfileContract.Presenter {

    private final ProfileContract.View mProfileView;


    private User repository = new User();
    private Context context;
    private UserRepository db;
    private String username;

    ProfilePresenter(ProfileContract.View mProfileView) {
        this.mProfileView = mProfileView;
        this.mProfileView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void start(String username) {
        this.username = username;
    }

    @Override
    public void loadProfile(Context context,boolean forceUpdate) {
        loadProfile(forceUpdate, true);
        this.context = context;
        db = UserRepository.getInstance(context);
    }

    @Override
    public void onEditNim(User user, String nim) {
        user.setNim(nim);
        updateProfile(user);
    }

    @Override
    public void onEditUsername(User user, String username) {
        user.setUsername(username);
        updateProfile(user);
    }

    @Override
    public void onEditClass(User user, String kelas) {
        user.setKelas(kelas);
        updateProfile(user);
    }

    @Override
    public void onEditDescription(User user, String description) {
        user.setDescription(description);
        updateProfile(user);
    }

    private void updateProfile(User user){
        if (db.updateUser(user)){
            loadProfile(context,true);
        } else {
            Toast.makeText(context,"Update Failed",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadProfile(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mProfileView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            repository = db.getUser(username);
            Preferences.getInstance(context).saveLogin(repository);
        } else {
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
