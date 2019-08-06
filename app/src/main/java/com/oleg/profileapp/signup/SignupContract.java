package com.oleg.profileapp.signup;

import com.oleg.profileapp.BasePresenter;
import com.oleg.profileapp.BaseView;
import com.oleg.profileapp.model.User;

public class SignupContract {
    interface Presenter extends BasePresenter {
        void signup(User user);

    }

    interface View extends BaseView<Presenter>{
        void onSuccessSignUp();

        void onFailedSignUp();

        void setLoadingIndicator(boolean active);
    }
}
