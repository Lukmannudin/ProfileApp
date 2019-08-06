package com.oleg.profileapp.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.oleg.profileapp.R;
import com.oleg.profileapp.login.LoginActivity;
import com.oleg.profileapp.main.MainActivity;

import me.relex.circleindicator.CircleIndicator;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class OnBoardingActivity extends AppCompatActivity {
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    CircleIndicator indicator;
    TextView btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        btnFinish = findViewById(R.id.btn_finish);

        viewPager = findViewById(R.id.vp_onboarding);
        pagerAdapter = new OnBoardingAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        indicator = findViewById(R.id.indicator);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        setupViewPager(viewPager);

        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        OnBoardingAdapter pagerAdapter = new OnBoardingAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ScreenFragmentSatu());
        pagerAdapter.addFragment(new ScreenFragmentDua());
        pagerAdapter.addFragment(new ScreenFragmentTiga());
        viewPager.setAdapter(pagerAdapter);
        indicator.setViewPager(viewPager);

    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
