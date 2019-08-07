package com.oleg.profileapp.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oleg.profileapp.R;
import com.oleg.profileapp.login.LoginActivity;
import com.oleg.profileapp.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Tanggal Pengerjaan : 7 Agustus 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8
public class SignupActivity extends AppCompatActivity implements SignupContract.View {
    ProgressBar progressBar;
    EditText edtNim, edtUsername, edtPassword, edtRetypePassword, edtClass, edtTelephone, edtEmail, edtTwitter, edtFacebook, edtInstagram;
    Button btnSignup;

    private SignupContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
        mPresenter = new SignupPresenter(this);

        btnSignup.setOnClickListener(view -> {

            if (foundErrorInForm()) {
                User user = new User();
                user.setNim(edtNim.getText().toString());
                user.setUsername(edtUsername.getText().toString());
                user.setKelas(edtClass.getText().toString());
                user.setTelepon(edtTelephone.getText().toString());
                user.setEmail(edtEmail.getText().toString());
                user.setTwitter(edtTwitter.getText().toString());
                user.setInstagram(edtInstagram.getText().toString());
                user.setFacebook(edtFacebook.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                mPresenter.signup(user);
            }
        });

    }


    boolean foundErrorInForm() {
        boolean statusForm = true;

        if (edtNim.getText().toString().isEmpty()) {
            edtNim.setError(getResources().getString(R.string.nim_error));
            statusForm = false;
        }
        if (edtUsername.getText().toString().isEmpty()) {
            edtUsername.setError(getResources().getString(R.string.username_error));
            statusForm = false;
        }
        if (edtClass.getText().toString().isEmpty()) {
            edtClass.setError(getResources().getString(R.string.class_error));
            statusForm = false;
        }
        if (edtTelephone.getText().toString().isEmpty()) {
            edtTelephone.setError(getResources().getString(R.string.telephone_error));
            statusForm = false;
        }
        if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError(getResources().getString(R.string.email_error));
            statusForm = false;
        }
        if (edtTwitter.getText().toString().isEmpty()) {
            edtTwitter.setError(getResources().getString(R.string.twitter_error));
            statusForm = false;
        }
        if (edtInstagram.getText().toString().isEmpty()) {
            edtInstagram.setError(getResources().getString(R.string.instagram_error));
            statusForm = false;
        }
        if (edtFacebook.getText().toString().isEmpty()) {
            edtFacebook.setError(getResources().getString(R.string.facebook_error));
            statusForm = false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(getResources().getString(R.string.password_error));
            statusForm = false;
        }
        if (!edtRetypePassword.getText().toString().equals(edtRetypePassword.getText().toString())) {
            edtRetypePassword.setError(getResources().getString(R.string.retype_password_error));
            statusForm = false;
        }
        if (!isEmailValid(edtEmail.getText().toString())) {
            edtEmail.setError(getResources().getString(R.string.email_not_valid));
            statusForm = false;
        }

        if (mPresenter.checkAlreadyAccount(edtUsername.getText().toString())){
            edtUsername.setError(getResources().getString(R.string.account_already_exist));
            statusForm = false;
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
    public void setLoadingIndicator(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onSuccessSignUp() {
        Toast.makeText(this, "Congratulations! Account Created", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onFailedSignUp() {
        Toast.makeText(this, "Sorry, account created failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SignupContract.Presenter presenter) {
        mPresenter = presenter;
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

        btnSignup = findViewById(R.id.login_btn_signup);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
