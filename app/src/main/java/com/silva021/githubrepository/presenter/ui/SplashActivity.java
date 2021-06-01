package com.silva021.githubrepository.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.silva021.githubrepository.R;
import com.silva021.githubrepository.presenter.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements Runnable{
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.postDelayed(this, 4000);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        run();
    }

    @Override
    public void run() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}