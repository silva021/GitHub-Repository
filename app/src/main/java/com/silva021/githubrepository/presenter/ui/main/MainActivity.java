package com.silva021.githubrepository.presenter.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silva021.githubrepository.R;
import com.silva021.githubrepository.presenter.adapter.RepositoryAdapter;
import com.silva021.githubrepository.databinding.ActivityMainBinding;
import com.silva021.githubrepository.data.model.*;
import com.silva021.githubrepository.presenter.ui.commit.CommitsActivity;
import com.silva021.githubrepository.presenter.ui.login.LoginActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositoryAdapter.OnItemClickListener {
    private ActivityMainBinding mBinding;
    private RepositoryAdapter mRepositoryAdapter;
    private MainViewModel mainViewModel;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (getIntent().getExtras() != null) {
            mUser = (User) getIntent().getExtras().getSerializable("object");
            updateView(mUser);
        }

        mBinding.imgLogout.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }


    private void updateView(User user) {
        Glide.with(getApplicationContext())
                .load(user.getAvatarUrl())
                .placeholder(R.drawable.ic_github)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mBinding.imgProfitePhoto);

        mBinding.txtUser.setText(user.getLogin());


        mainViewModel.getListOfRepository(user.getLogin()).observe(this, repositoryList -> {
            if (repositoryList != null) {
                initRecycler(repositoryList);
            }
        });
    }

    private void initRecycler(List<Repository> list) {
        mRepositoryAdapter = new RepositoryAdapter(list, getApplicationContext());
        mRepositoryAdapter.setOnItemClickListener(this::onItemClick);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.recyclerView.setAdapter(mRepositoryAdapter);
    }

    @Override
    public void onItemClick(@NonNull View view, int position) {
        Repository repository = mRepositoryAdapter.getRepository(position);
        startActivity(new Intent(MainActivity.this, CommitsActivity.class).putExtra("object", repository));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
}