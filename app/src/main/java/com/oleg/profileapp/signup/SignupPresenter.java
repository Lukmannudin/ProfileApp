package com.oleg.profileapp.signup;

import android.content.Context;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.UserRepository;


// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class SignupPresenter implements SignupContract.Presenter {
    private SignupContract.View mView;
    private UserRepository db;

    SignupPresenter(SignupContract.View mView) {
        this.mView = mView;
        db = UserRepository.getInstance((Context) mView);
    }


    @Override
    public void signup(User user) {
        if (!db.createUser(user)){
            mView.onFailedSignUp();
        } else {
            mView.onSuccessSignUp();
        }
    }

    @Override
    public boolean checkAlreadyAccount(String username) {
        return db.getUser(username)!= null;
    }

    @Override
    public void start() {
        mView.setLoadingIndicator(false);
    }
}
