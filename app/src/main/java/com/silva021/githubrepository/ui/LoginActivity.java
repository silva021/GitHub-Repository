package com.silva021.githubrepository.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.silva021.githubrepository.R;
import com.silva021.githubrepository.api.GitHubService;
import com.silva021.githubrepository.api.ServiceGenerator;
import com.silva021.githubrepository.databinding.ActivityLoginBinding;
import com.silva021.githubrepository.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.btnSignIn.setOnClickListener(view -> verifyUser(mBinding.edtUser.getText().toString()));
    }

    boolean verifyFields() {
        mBinding.inpLayoutEdtUser.setError("Insira primeiro um usuário válido");
        mBinding.inpLayoutEdtUser.setErrorEnabled(mBinding.edtUser.getText().toString().isEmpty());
        return !mBinding.edtUser.getText().toString().isEmpty();
    }

    private void verifyUser(String user) {
        if (verifyFields()) {
            ServiceGenerator.createService(GitHubService.class).getUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 200) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("object", response.body()));
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                    else
                        Snackbar.make(getCurrentFocus(), "Esse usuário não foi encontrado", Snackbar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE).show();

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}