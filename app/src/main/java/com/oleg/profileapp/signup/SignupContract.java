package com.oleg.profileapp.signup;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.User;

// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class SignupContract {
    interface Presenter extends BasePresenter {
        void signup(User user);

        boolean checkAlreadyAccount(String username);
    }

    interface View extends BaseView<Presenter>{
        void onSuccessSignUp();

        void onFailedSignUp();

        void setLoadingIndicator(boolean active);
    }
}
