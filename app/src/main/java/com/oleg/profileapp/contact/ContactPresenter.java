package com.oleg.profileapp.contact;

import android.content.Context;
import android.widget.Toast;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.UserRepository;
import com.oleg.profileapp.util.Preferences;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ContactPresenter implements ContactContract.Presenter {
    private final ContactContract.View mContactView;

    private User repository = new User();
    private Context context;
    private UserRepository db;
    private String username;


    public ContactPresenter(ContactContract.View mContactView) {
        this.mContactView = mContactView;
        this.mContactView.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void start(String username) {
        this.username = username;

    }

    @Override
    public void loadContact(Context context, Boolean forceUpdate) {
        loadContact(forceUpdate, true);
        this.context = context;
        db = UserRepository.getInstance(context);
    }

    public void loadContact(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mContactView.setLoadingIndicator(true);
        }

        Preferences preferences = Preferences.getInstance(context);
        repository = preferences.getUserLogin();

        if (forceUpdate) {
            repository = db.getUser(repository.getUsername());
            Preferences.getInstance(context).saveLogin(repository);
        }


        if (showLoadingUI) {
            mContactView.setLoadingIndicator(false);
        }

        processProfile(repository);

    }

    private void processProfile(User users) {
        mContactView.showContact(users);
    }

    @Override
    public void onEditTelepon(User user, String telepon) {
        user.setTelepon(telepon);
        updateProfile(user);
    }

    @Override
    public void onEditEmail(User user, String email) {
        user.setEmail(email);
        updateProfile(user);
    }

    @Override
    public void onEditInstagram(User user, String instagram) {
        user.setInstagram(instagram);
        updateProfile(user);
    }

    @Override
    public void onEditTwitter(User user, String twitter) {
        user.setTwitter(twitter);
        updateProfile(user);
    }

    @Override
    public void onEditFacebook(User user, String facebook) {
        user.setFacebook(facebook);
        updateProfile(user);
    }

    private void updateProfile(User user) {
        if (db.updateUser(user)) {
            loadContact(context, true);
        } else {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
