package com.oleg.profileapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.oleg.profileapp.R;
import com.oleg.profileapp.main.MainActivity;
import com.oleg.profileapp.model.User;
import com.oleg.profileapp.signup.SignupActivity;
import com.oleg.profileapp.util.Preferences;

import java.util.Objects;

// Tanggal Pengerjaan : 19 Mei 2019
// NIM : 10116347
// Nama : Lukmannudin
// Kelas :IF - 8

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    Button btnLogin;
    ProgressBar progressBar;

    EditText edtUsername, edtPassword;
    TextView tvToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        if (onLoginSucceed()) {
            startActivity(new Intent(this, MainActivity.class));
        }

        mPresenter = new LoginPresenter(this);

        tvToSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> mPresenter.requestLogin(
                Objects.requireNonNull(edtUsername.getText()).toString(),
                Objects.requireNonNull(edtPassword.getText()).toString()
        ));

    }

    boolean onLoginSucceed() {
        Preferences preferences = Preferences.getInstance(this);
        User user = preferences.getUserLogin();
        return !user.getUsername().equals("") && !user.getPassword().equals("");
    }

    @Override
    public void onLoginSuccess(User user) {
        Preferences preferences = Preferences.getInstance(this);
        preferences.saveLogin(user);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Username or Password wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoadingIndicator(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void initView() {
        tvToSignUp = findViewById(R.id.login_to_signup);
        btnLogin = findViewById(R.id.login_btn_login);
        edtUsername = findViewById(R.id.login_edt_username);
        edtPassword = findViewById(R.id.login_edt_password);
        progressBar = findViewById(R.id.login_proggressbar);
    }
}
