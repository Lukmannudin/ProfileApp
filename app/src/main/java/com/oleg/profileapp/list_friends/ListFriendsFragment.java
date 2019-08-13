package com.oleg.profileapp.list_friends;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.oleg.profileapp.R;
import com.oleg.profileapp.list_friends.AddFriend.AddFriendActivity;
import com.oleg.profileapp.model.Friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;


// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsFragment extends Fragment implements ListFriendsContract.View {

    private ListFriendsContract.Presenter mPresenter;
    private List<Friend> friendList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnUpdate;
    private FrameLayout frameLayout;
    private Dialog dialog;
    private AlertDialog.Builder alertDialog;
    private FloatingActionButton fbBtn;
    private TextView nim, nama, kelas, email, telepon, instagram, facebook, twitter;

    LinearLayout linearLayout;


    public ListFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ListFriendsPresenter(frameLayout, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_friends, container, false);
        recyclerView = view.findViewById(R.id.rv_listFriends);
        frameLayout = view.findViewById(R.id.listFriendLayout);
        fbBtn = view.findViewById(R.id.list_friends_addbtn);
        alertDialog = new AlertDialog.Builder(getContext());

        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListFriendsAdapter(friendList, mItemListener);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                1);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        recyclerView.setAdapter(mAdapter);

        fbBtn.setOnClickListener(view -> addFriend());
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showListFriends(List<Friend> friends) {
        friendList.clear();
        friendList.addAll(friends);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFriendDetailUI(List<Friend> friends, Friend friend, int index) {
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

        btnUpdate = dialog.findViewById(R.id.btn_update);

        nim.setText(friend.getNim());
        nama.setText(friend.getUsername());
        kelas.setText(friend.getKelas());
        email.setText(friend.getEmail());
        telepon.setText(friend.getTelepon());
        instagram.setText(friend.getInstagram());
        twitter.setText(friend.getTwitter());
        facebook.setText(friend.getFacebook());

        btnUpdate.setOnClickListener(v -> {
            friend.setNim(nim.getText().toString());
            friend.setUsername(nama.getText().toString());
            friend.setKelas(kelas.getText().toString());
            friend.setEmail(email.getText().toString());
            friend.setTelepon(telepon.getText().toString());
            friend.setInstagram(instagram.getText().toString());
            friend.setTwitter(twitter.getText().toString());
            friend.setFacebook(facebook.getText().toString());

            mPresenter.onEditFriend(friend);

            mAdapter = new ListFriendsAdapter(friends, mItemListener);
            recyclerView.setAdapter(mAdapter);
            dialog.dismiss();
            Snackbar.make(frameLayout, "Friend Updated", Snackbar.LENGTH_LONG).show();
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
        mPresenter.start(getActivity());
    }


    // Saat salah satu item pada list di klik
    private ListFriendsListener mItemListener = new ListFriendsListener() {
        @Override
        public void onListFriendClick(List<Friend> friends, Friend clickedListFriend, int index) {
            mPresenter.openDetailFriendDetail(friends, clickedListFriend, index);
        }

        @Override
        public void onBtnCallClick(Friend clickedListFriend) {
            mPresenter.onCallFriend(clickedListFriend);
        }

        @Override
        public void onBtnDeleteClick(Friend clickedFriend) {
            onDeleteFriend(clickedFriend);
        }
    };

    private DialogInterface.OnClickListener dialogYesOrNoListener(Friend clickeListFriend) {
        return (dialogInterface, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    mPresenter.onDeleteFriend(clickeListFriend);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };
    }

    private void onDeleteFriend(Friend clickedFriend) {
        if (getActivity() != null) {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this file!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener(sDialog -> {
                        sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        mPresenter.onDeleteFriend(clickedFriend);
                    })
                    .show();
        } else {
            onDeleteDefault(clickedFriend);
        }
    }

    private void onDeleteDefault(Friend clickedFriend) {
        alertDialog.setMessage("Are you sure?").setPositiveButton("YES", dialogYesOrNoListener(clickedFriend))
                .setNegativeButton("No", dialogYesOrNoListener(clickedFriend)).show();
    }

    private void addFriend() {
        startActivity(new Intent(getContext(), AddFriendActivity.class));
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(frameLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessageSuccess(Friend friend, String message) {
        Snackbar.make(frameLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showMessageFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void callFriend(Intent intent) {
        startActivity(intent);
    }

    interface ListFriendsListener {
        void onListFriendClick(List<Friend> users, Friend clickedListUser, int index);

        void onBtnCallClick(Friend clickedListUser);

        void onBtnDeleteClick(Friend clickedListUser);
    }
}
