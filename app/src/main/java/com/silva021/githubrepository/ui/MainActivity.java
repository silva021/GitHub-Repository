package com.silva021.githubrepository.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silva021.githubrepository.R;
import com.silva021.githubrepository.adapter.RepositoryAdapter;
import com.silva021.githubrepository.api.GitHubService;
import com.silva021.githubrepository.api.ServiceGenerator;
import com.silva021.githubrepository.databinding.ActivityMainBinding;
import com.silva021.githubrepository.model.Repository;
import com.silva021.githubrepository.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RepositoryAdapter.OnItemClickListener{
    ActivityMainBinding mBinding;
    private RepositoryAdapter mRepositoryAdapter;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

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


        ServiceGenerator.createService(GitHubService.class).getRepositories(user.getLogin()).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                initRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
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