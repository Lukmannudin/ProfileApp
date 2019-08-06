package com.oleg.profileapp.login;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.User;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
class LoginContract {

    interface View extends BaseView<Presenter>{

        void onLoginSuccess(User user);

        void onLoginFailed();

        void showLoadingIndicator(boolean active);

    }

    interface Presenter extends BasePresenter {

        void requestLogin(String username, String password);

    }

}
