package com.oleg.profileapp.login;

import android.content.Context;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.UserRepository;


// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class LoginPresenter implements LoginContract.Presenter {


    private final LoginContract.View mLoginView;

    private UserRepository db;

    public LoginPresenter(LoginContract.View mLoginView) {
        this.mLoginView = mLoginView;
        db = UserRepository.getInstance((Context) mLoginView);
    }

    @Override
    public void start() {
        mLoginView.showLoadingIndicator(false);
    }

    @Override
    public void requestLogin(String username, String password) {
        mLoginView.showLoadingIndicator(true);
        User user = db.getUser(username, password);
        if (user == null) {
            mLoginView.onLoginFailed();
        } else {
            processLogin(user);
        }
        mLoginView.showLoadingIndicator(false);
    }

    private void processLogin(User user) {
        mLoginView.onLoginSuccess(user);
    }


}
