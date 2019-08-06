package com.oleg.profileapp.signup;

import android.content.Context;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.repo.Repository;

public class SignupPresenter implements SignupContract.Presenter {
    private SignupContract.View mView;
    private Repository db;

    SignupPresenter(SignupContract.View mView) {
        this.mView = mView;
        db = Repository.getInstance((Context) mView);
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
    public void start() {
        mView.setLoadingIndicator(false);
    }
}
