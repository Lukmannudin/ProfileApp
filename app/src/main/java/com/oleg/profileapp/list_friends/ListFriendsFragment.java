package com.oleg.profileapp.list_friends;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsFragment extends Fragment implements ListFriendsContract.View {

    private ListFriendsContract.Presenter mPresenter;
    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnEdit;
    private FrameLayout frameLayout;

    TextView nim, nama, kelas, email, telepon, instagram, facebook, twitter;
    LinearLayout linearLayout;


    public ListFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ListFriendsPresenter(frameLayout,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_friends, container, false);
        recyclerView = view.findViewById(R.id.rv_listFriends);
        frameLayout = view.findViewById(R.id.listFriendLayout);
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListFriendsAdapter(userList, mItemListener);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                1);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showListFriends(List<User> users) {
        userList.clear();
        userList.addAll(users);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFriendDetailUI(List<User> users, User user, int index) {
        // intent ke user detail
        final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        dialog.setContentView(R.layout.activity_friend_detail);
        dialog.setTitle("My Dialog");

        nim = dialog.findViewById(R.id.tv_friend_detail_nim);
        nama = dialog.findViewById(R.id.tv_friend_detail_nama);
        kelas = dialog.findViewById(R.id.tv_friend_detail_kelas);
        email = dialog.findViewById(R.id.tv_friend_detail_email);
        telepon = dialog.findViewById(R.id.tv_friend_detail_telepon);
        instagram = dialog.findViewById(R.id.tv_friend_detail_instagram);
        twitter = dialog.findViewById(R.id.tv_friend_detail_twitter);
        facebook = dialog.findViewById(R.id.tv_friend_detail_facebook);

        btnEdit = dialog.findViewById(R.id.btn_edit);

        nim.setText(user.getNim());
        nama.setText(user.getUsername());
        kelas.setText(user.getKelas());
        email.setText(user.getEmail());
        telepon.setText(user.getTelepon());
        instagram.setText(user.getInstagram());
        twitter.setText(user.getTwitter());
        facebook.setText(user.getFacebook());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNim(nim.getText().toString());
                user.setUsername(nama.getText().toString());
                user.setKelas(kelas.getText().toString());
                user.setEmail(email.getText().toString());
                user.setTelepon(telepon.getText().toString());
                user.setInstagram(instagram.getText().toString());
                user.setTwitter(twitter.getText().toString());
                user.setFacebook(facebook.getText().toString());
                users.set(index, user);
                mAdapter = new ListFriendsAdapter(users,mItemListener);
                recyclerView.setAdapter(mAdapter);
                dialog.dismiss();
                    Snackbar.make(frameLayout,"Data Berhasil Diubah",Snackbar.LENGTH_LONG).show();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void setPresenter(ListFriendsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    // Saat salah satu item pada list di klik
    ListFriendsListener mItemListener = new ListFriendsListener() {
        @Override
        public void onListFriendClick(List<User> users, User clickedListUser, int index) {
            mPresenter.openDetailFriendDetail(users, clickedListUser, index);

        }

        @Override
        public void onBtnCallClick(User clickedListUser) {
            mPresenter.onCallFriend(clickedListUser);
        }

        @Override
        public void onBtnDeleteClick(User clickedListUser) {
            mPresenter.onDeleteFriend(clickedListUser);
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_friend_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
           case R.id.tambah_teman:
               final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
               dialog.setContentView(R.layout.activity_friend_detail);
               dialog.setTitle("Tambah Teman");
               nim = dialog.findViewById(R.id.tv_friend_detail_nim);
               nama = dialog.findViewById(R.id.tv_friend_detail_nama);
               kelas = dialog.findViewById(R.id.tv_friend_detail_kelas);
               email = dialog.findViewById(R.id.tv_friend_detail_email);
               telepon = dialog.findViewById(R.id.tv_friend_detail_telepon);
               instagram = dialog.findViewById(R.id.tv_friend_detail_instagram);
               twitter = dialog.findViewById(R.id.tv_friend_detail_twitter);
               facebook = dialog.findViewById(R.id.tv_friend_detail_facebook);

               btnEdit = dialog.findViewById(R.id.btn_edit);
               btnEdit.setText("Tambah");

               User user = new User();

               nim.setText(user.getNim());
               nama.setText(user.getUsername());
               kelas.setText(user.getKelas());
               email.setText(user.getEmail());
               telepon.setText(user.getTelepon());
               instagram.setText(user.getInstagram());
               twitter.setText(user.getTwitter());
               facebook.setText(user.getFacebook());

               btnEdit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       user.setNim(nim.getText().toString());
                       user.setUsername(nama.getText().toString());
                       user.setKelas(kelas.getText().toString());
                       user.setEmail(email.getText().toString());
                       user.setTelepon(telepon.getText().toString());
                       user.setInstagram(instagram.getText().toString());
                       user.setTwitter(twitter.getText().toString());
                       user.setFacebook(facebook.getText().toString());


                       if (nim.getText().toString().isEmpty() || nama.getText().toString().isEmpty() || kelas.getText().toString().isEmpty()
                            || telepon.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                       ){
                            Toast.makeText(getActivity(),"Tolong lengkapi isian anda!",Toast.LENGTH_LONG).show();
                       } else {
                           userList.add(user);
                           mAdapter = new ListFriendsAdapter(userList,mItemListener);
                           recyclerView.setAdapter(mAdapter);
                           Snackbar.make(frameLayout,"Data Berhasil Ditambahkan",Snackbar.LENGTH_LONG).show();
                       }

                       dialog.dismiss();
                   }
               });

               dialog.show();
               Window window = dialog.getWindow();
               assert window != null;
               window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(frameLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void callFriend(Intent intent) {
        startActivity(intent);
    }

    interface ListFriendsListener {
        void onListFriendClick(List<User> users, User clickedListUser, int index);

        void onBtnCallClick(User clickedListUser);

        void onBtnDeleteClick(User clickedListUser);
    }
}
