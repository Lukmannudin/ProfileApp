package com.oleg.profileapp.list_friends;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oleg.profileapp.model.Friend;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.R;

import java.util.List;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class ListFriendsAdapter extends RecyclerView.Adapter<ListFriendsAdapter.ViewHolder> {

    private List<Friend> mDataSet;
    private ListFriendsFragment.ListFriendsListener listener;

    ListFriendsAdapter(List<Friend> mDataSet, ListFriendsFragment.ListFriendsListener listener) {
        this.mDataSet = mDataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(mDataSet.get(position),listener, position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView btnDelete;
        ImageView btnCalling;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_friend_nama);
            btnDelete = itemView.findViewById(R.id.btn_friend_delete);
            btnCalling = itemView.findViewById(R.id.btnTelepon);
        }

        void bindItem(final Friend friend, final ListFriendsFragment.ListFriendsListener listener, int position){
            tvName.setText(friend.getUsername());
            btnDelete.setOnClickListener(v -> listener.onBtnDeleteClick(friend));

            btnCalling.setOnClickListener(v -> listener.onBtnCallClick(friend));

            itemView.setOnClickListener(v -> listener.onListFriendClick(mDataSet, friend, position));

        }

    }
}
