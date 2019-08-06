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

public class ScreenFragmentSatu extends Fragment {
    TextView textProfile;
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_screen_fragment_satu, container, false);
        textProfile = view.findViewById(R.id.profile_text_onboard);
        Animation bottomToTop = AnimationUtils.loadAnimation(getActivity(),R.anim.bottom_to_top);
        Animation bottomToTop2 = AnimationUtils.loadAnimation(getActivity(),R.anim.bottom_to_top2);

        textProfile.startAnimation(bottomToTop);
        imageView = view.findViewById(R.id.iv_profile_onboarding);
        imageView.startAnimation(bottomToTop2);
        return view;
    }
}
