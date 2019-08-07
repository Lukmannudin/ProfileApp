package com.oleg.profileapp.contact;

import android.content.Context;

import com.oleg.profileapp.model.User;
import com.oleg.profileapp.util.Preferences;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ContactPresenter implements ContactContract.Presenter {
    private final ContactContract.View mContactView;
    private boolean mFirstLoad = true;

    private User repository = new User();
    private Context context;

    public ContactPresenter(ContactContract.View mContactView) {
        this.mContactView = mContactView;
        this.mContactView.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void loadContact(Context context, Boolean forceUpdate) {
        loadContact(forceUpdate || mFirstLoad, true);
        this.context = context;
    }

    public void loadContact(boolean forceUpdate, final boolean showLoadingUI){
        if (showLoadingUI){
            mContactView.setLoadingIndicator(true);
        }

        if (forceUpdate){
            Preferences preferences = Preferences.getInstance(context);
            repository = preferences.getUserLogin();
        }


        if (showLoadingUI){
            mContactView.setLoadingIndicator(false);
        }

        processProfile(repository);




    }

    public void processProfile(User users){
        mContactView.showContact(users);
    }
}
