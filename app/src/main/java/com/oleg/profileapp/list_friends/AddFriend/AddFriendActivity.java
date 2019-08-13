package com.oleg.profileapp.list_friends.AddFriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.oleg.profileapp.R;
import com.oleg.profileapp.main.MainActivity;
import com.oleg.profileapp.model.Friend;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class AddFriendActivity extends AppCompatActivity implements AddFriendContract.View{
    ProgressBar progressBar;
    TextView labelCreateAccount;

    EditText edtNim, edtUsername, edtPassword, edtRetypePassword, edtClass, edtTelephone, edtEmail, edtTwitter, edtFacebook, edtInstagram;
    Button btnAddFriend;
    private AddFriendContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
        mPresenter = new AddFriendPresenter(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Friend friend = new Friend();
        btnAddFriend.setOnClickListener(v->{
            if (!foundErrorInForm())
                onAddFriend(friend);
            else
                onFailedAdd();
        });
    }

    private void onAddFriend(Friend friend){
        friend.setNim(edtNim.getText().toString());
        friend.setUsername(edtUsername.getText().toString());
        friend.setKelas(edtClass.getText().toString());
        friend.setEmail(edtEmail.getText().toString());
        friend.setTelepon(edtTelephone.getText().toString());
        friend.setInstagram(edtInstagram.getText().toString());
        friend.setTwitter(edtTwitter.getText().toString());
        friend.setFacebook(edtFacebook.getText().toString());
        mPresenter.addFriend(friend);
    }

    @Override
    public void onSuccessAdd() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success")
                .setContentText("Friend added successfully")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    startActivity(new Intent(this, MainActivity.class));
                })
                .show();
    }

    @Override
    public void onFailedAdd() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(AddFriendContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start(this);
    }

    private void initView() {
        progressBar = findViewById(R.id.signup_proggressbar);

        edtNim = findViewById(R.id.signup_edt_nim);
        edtUsername = findViewById(R.id.signup_edt_username);
        edtPassword = findViewById(R.id.signup_edt_password);
        edtRetypePassword = findViewById(R.id.signup_edt_retype_password);
        edtClass = findViewById(R.id.signup_edt_class);
        edtTelephone = findViewById(R.id.signup_edt_telephone);
        edtEmail = findViewById(R.id.signup_edt_email);
        edtTwitter = findViewById(R.id.signup_edt_twitter);
        edtFacebook = findViewById(R.id.signup_edt_facebook);
        edtInstagram = findViewById(R.id.signup_edt_instagram);
        labelCreateAccount = findViewById(R.id.login_label_create_account);

        btnAddFriend = findViewById(R.id.login_btn_signup);

        TextInputLayout tilpassword,tilretypePassword,tilDescription;
        tilpassword = findViewById(R.id.signup_til_password);
        tilretypePassword = findViewById(R.id.signup_til_retype_password);
        tilDescription = findViewById(R.id.signup_til_description);

        labelCreateAccount.setText("Add Friend");
        tilpassword.setVisibility(View.GONE);
        tilretypePassword.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tilDescription.setVisibility(View.GONE);
    }

    boolean foundErrorInForm() {
        boolean statusForm = false;

        if (edtNim.getText().toString().isEmpty()) {
            edtNim.setError(getResources().getString(R.string.nim_error));
            statusForm = true;
        }
        if (edtUsername.getText().toString().isEmpty()) {
            edtUsername.setError(getResources().getString(R.string.username_error));
            statusForm = true;
        }
        if (edtClass.getText().toString().isEmpty()) {
            edtClass.setError(getResources().getString(R.string.class_error));
            statusForm = true;
        }
        if (edtTelephone.getText().toString().isEmpty()) {
            edtTelephone.setError(getResources().getString(R.string.telephone_error));
            statusForm = true;
        }
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError(getResources().getString(R.string.email_error));
            statusForm = true;
        }
        if (edtTwitter.getText().toString().isEmpty()) {
            edtTwitter.setError(getResources().getString(R.string.twitter_error));
            statusForm = true;
        }
        if (edtInstagram.getText().toString().isEmpty()) {
            edtInstagram.setError(getResources().getString(R.string.instagram_error));
            statusForm = true;
        }
        if (edtFacebook.getText().toString().isEmpty()) {
            edtFacebook.setError(getResources().getString(R.string.facebook_error));
            statusForm = true;
        }
        if (!isEmailValid(edtEmail.getText().toString())) {
            edtEmail.setError(getResources().getString(R.string.email_not_valid));
            statusForm = true;
        }

        return statusForm;


    }
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
