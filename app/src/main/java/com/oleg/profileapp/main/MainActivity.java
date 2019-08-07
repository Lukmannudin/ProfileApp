package com.oleg.profileapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oleg.profileapp.R;
import com.oleg.profileapp.contact.ContactFragment;
import com.oleg.profileapp.list_friends.ListFriendsFragment;
import com.oleg.profileapp.login.LoginActivity;
import com.oleg.profileapp.profile.ProfileFragment;
import com.oleg.profileapp.util.Preferences;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class MainActivity extends AppCompatActivity {
    private int menuProfile = 0;
    private int menuContact = 1;
    private int menuListFriends = 2;

    BottomNavigationView bottomNavigationView;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.vp_main);

        bottomNavigationView = findViewById(R.id.bnv_main);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itemProfile:
                        mPager.setCurrentItem(menuProfile);
                        break;
                    case R.id.itemContact:
                        mPager.setCurrentItem(menuContact);
                        break;
                    case R.id.itemListFriends:
                        mPager.setCurrentItem(menuListFriends);
                }
                return false;
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            MenuItem prevMenuItem;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                MenuItem bottomIconActive = bottomNavigationView.getMenu().getItem(position);
                bottomIconActive.setChecked(true);
                prevMenuItem = bottomIconActive;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mPager);
        mPager.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPager.getCurrentItem() == 0){
            super.onBackPressed();
            finishAffinity();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        MainAdapter pagerAdapter = new MainAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ProfileFragment());
        pagerAdapter.addFragment(new ContactFragment());
        pagerAdapter.addFragment(new ListFriendsFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_menu_logout){
            Preferences preferences = Preferences.getInstance(this);
            preferences.deleteLogin();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}


