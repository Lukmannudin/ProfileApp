package com.oleg.profileapp.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.oleg.profileapp.R;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ScreenFragmentDua extends Fragment {
    ImageView ivContact;
    TextView textContact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_screen_fragment_dua, container, false);
        ivContact = view.findViewById(R.id.iv_contact_onboarding);
        textContact = view.findViewById(R.id.tv_contact_onboarding);

        Animation bottomToTop = AnimationUtils.loadAnimation(getActivity(),R.anim.bottom_to_top);
        Animation bottomToTop2 = AnimationUtils.loadAnimation(getActivity(),R.anim.bottom_to_top2);

        ivContact.startAnimation(bottomToTop);
        textContact.startAnimation(bottomToTop2);

        return view;
    }

}
