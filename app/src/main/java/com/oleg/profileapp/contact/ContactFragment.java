package com.oleg.profileapp.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.oleg.profileapp.R;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.util.Preferences;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ContactFragment extends Fragment implements ContactContract.View {
    private ProgressBar progressBar;
    private ContactContract.Presenter mPresenter;
    private TextView telepon, email, instagram, twitter, facebook;
    private BottomSheetBehavior sheetBehavior;
    private ConstraintLayout bottomSheet;
    private Button btnCancel, btnSave;
    private TextView labelBottomSheet;
    private EditText editTextBottomSheet;
    private User user;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ContactPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        telepon = view.findViewById(R.id.tv_contact_telepon);
        email = view.findViewById(R.id.tv_contact_email);
        instagram = view.findViewById(R.id.tv_contact_instagram);
        twitter = view.findViewById(R.id.tv_contact_twitter);
        facebook = view.findViewById(R.id.tv_contact_facebook);

        btnCancel = view.findViewById(R.id.profile_bottom_sheet_btnCancel);
        btnSave = view.findViewById(R.id.profile_bottom_sheet_btnSave);
        labelBottomSheet = view.findViewById(R.id.profile_bottom_sheet_label);
        editTextBottomSheet = view.findViewById(R.id.profile_bottom_sheet_editText);

        bottomSheet = view.findViewById(R.id.profile_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        telepon.setOnClickListener(view1 -> bottomSheetBehavior(TELEPON));
        email.setOnClickListener(view1 -> bottomSheetBehavior(EMAIL));
        instagram.setOnClickListener(view1 -> bottomSheetBehavior(INSTAGRAM));
        twitter.setOnClickListener(view1 -> bottomSheetBehavior(TWITTER));
        facebook.setOnClickListener(view1 -> bottomSheetBehavior(FACEBOOK));

        progressBar = view.findViewById(R.id.pb_contact);

        return view;
    }

    private void bottomSheetBehavior(String purpose) {

        labelBottomSheet.setText(getString(R.string.update_bottom_sheet_label, purpose));

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED || sheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        switch (purpose) {
            case TELEPON:
                editTextBottomSheet.setText(user.getTelepon());
                break;
            case EMAIL:
                editTextBottomSheet.setText(user.getEmail());
                break;
            case INSTAGRAM:
                editTextBottomSheet.setText(user.getInstagram());
                break;
            case TWITTER:
                editTextBottomSheet.setText(user.getTwitter());
                break;
            case FACEBOOK:
                editTextBottomSheet.setText(user.getFacebook());
                break;
        }


        btnCancel.setOnClickListener(view -> sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
        btnSave.setOnClickListener(view -> {
            String dataForUpdate = editTextBottomSheet.getText().toString();
            switch (purpose) {
                case TELEPON:
                    mPresenter.onEditTelepon(user, dataForUpdate);
                    break;
                case EMAIL:
                    mPresenter.onEditEmail(user, dataForUpdate);
                    break;
                case INSTAGRAM:
                    mPresenter.onEditInstagram(user, dataForUpdate);
                    break;
                case TWITTER:
                    mPresenter.onEditTwitter(user, dataForUpdate);
                    break;
                case FACEBOOK:
                    mPresenter.onEditFacebook(user, dataForUpdate);
                    break;
            }
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start(Preferences.getInstance(getContext()).getUserLogin().getUsername());
    }

    @Override
    public void showContact(User users) {
        telepon.setText(users.getTelepon());
        email.setText(users.getEmail());
        instagram.setText(users.getInstagram());
        twitter.setText(users.getTwitter());
        facebook.setText(users.getFacebook());
        user = users;
    }

    @Override
    public void setPresenter(ContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadContact(getActivity(), false);
    }

    static final String TELEPON = "telepon";
    static final String EMAIL = "email";
    static final String INSTAGRAM = "instagram";
    static final String TWITTER = "twitter";
    static final String FACEBOOK = "facebook";

}
