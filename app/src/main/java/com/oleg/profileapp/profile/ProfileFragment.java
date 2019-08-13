package com.oleg.profileapp.profile;

import android.os.Bundle;
import android.util.Log;
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
public class ProfileFragment extends Fragment implements ProfileContract.View {

    private ProfileContract.Presenter mPresenter;
    private ProgressBar progressBar;
    private TextView nim, nama, kelas, deskripsi;
    private BottomSheetBehavior sheetBehavior;
    private ConstraintLayout bottomSheet;
    private Button btnCancel, btnSave;
    private TextView labelBottomSheet;
    private EditText editTextBottomSheet;
    private User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nim = view.findViewById(R.id.tvprofile_nim);
        nama = view.findViewById(R.id.tvprofile_nama);
        kelas = view.findViewById(R.id.tvprofile_kelas);
        deskripsi = view.findViewById(R.id.tvprofile_deskripsi);
        progressBar = view.findViewById(R.id.pb_profile);

        btnCancel = view.findViewById(R.id.profile_bottom_sheet_btnCancel);
        btnSave = view.findViewById(R.id.profile_bottom_sheet_btnSave);
        labelBottomSheet = view.findViewById(R.id.profile_bottom_sheet_label);
        editTextBottomSheet = view.findViewById(R.id.profile_bottom_sheet_editText);

        bottomSheet = view.findViewById(R.id.profile_bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        nim.setOnClickListener(view1 -> bottomSheetBehavior(NIM));
        nama.setOnClickListener(view1 -> bottomSheetBehavior(NAMA));
        kelas.setOnClickListener(view1 -> bottomSheetBehavior(KELAS));
        deskripsi.setOnClickListener(view1 -> bottomSheetBehavior(DESKRIPSI));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start(Preferences.getInstance(getContext()).getUserLogin().getUsername());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadProfile(getContext(), false);
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
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProfile(User profile) {
        nim.setText(profile.getNim());
        nama.setText(profile.getUsername());
        kelas.setText(profile.getKelas());
        deskripsi.setText(profile.getDescription());

        user = profile;
    }

    private void bottomSheetBehavior(String purpose) {

        labelBottomSheet.setText(getString(R.string.update_bottom_sheet_label, purpose));

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED || sheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        switch (purpose) {
            case NIM:
                editTextBottomSheet.setText(user.getNim());
                break;
            case NAMA:
                editTextBottomSheet.setText(user.getUsername());
                break;
            case KELAS:
                editTextBottomSheet.setText(user.getKelas());
                break;
            case DESKRIPSI:
                editTextBottomSheet.setText(user.getDescription());
                break;
        }


        btnCancel.setOnClickListener(view -> sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN));
        btnSave.setOnClickListener(view -> {
            String dataForUpdate = editTextBottomSheet.getText().toString();
            switch (purpose) {
                case NIM:
                    mPresenter.onEditNim(user,dataForUpdate);
                    break;
                case NAMA:
                    mPresenter.onEditUsername(user,dataForUpdate);
                    break;
                case KELAS:
                    mPresenter.onEditClass(user,dataForUpdate);
                    break;
                case DESKRIPSI:
                    mPresenter.onEditDescription(user,dataForUpdate);
                    break;
            }
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        });
    }

    static final String NIM = "nim";
    static final String NAMA = "nama";
    static final String KELAS = "kelas";
    static final String DESKRIPSI = "deskripsi";
}
