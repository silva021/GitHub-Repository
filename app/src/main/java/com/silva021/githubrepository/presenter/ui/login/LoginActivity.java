package com.silva021.githubrepository.presenter.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.silva021.githubrepository.databinding.ActivityLoginBinding;
import com.silva021.githubrepository.presenter.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding mBinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mBinding.btnSignIn.setOnClickListener(view -> verifyUser(mBinding.edtUser.getText().toString()));
    }

    boolean verifyFields() {
        mBinding.inpLayoutEdtUser.setError("Insira primeiro um usuário válido");
        mBinding.inpLayoutEdtUser.setErrorEnabled(mBinding.edtUser.getText().toString().isEmpty());
        return !mBinding.edtUser.getText().toString().isEmpty();
    }

    private void verifyUser(String username) {
        if (verifyFields()) {
            loginViewModel.verifyUser(username).observe(this, user -> {
                if (user != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("object", user));
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {

                    Snackbar.make(getCurrentFocus(), "Esse usuário não foi encontrado", Snackbar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE).show();
                }
            });
        }
    }
}