package com.silva021.githubrepository.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.silva021.githubrepository.R;
import com.silva021.githubrepository.api.GitHubService;
import com.silva021.githubrepository.api.ServiceGenerator;
import com.silva021.githubrepository.databinding.ActivityCommitsBinding;
import com.silva021.githubrepository.model.Repository;
import com.silva021.githubrepository.model.RepositoryCommit;
import com.silva021.githubrepository.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitsActivity extends AppCompatActivity {
    Repository mRepository;
    ActivityCommitsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCommitsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        if (getIntent().getExtras() != null) {
            mRepository = (Repository) getIntent().getExtras().getSerializable("object");
            updateView(mRepository);
        }

        mBinding.imgBack.setOnClickListener(view -> finishActivity());
    }

    private void updateView(Repository repository) {
        mBinding.txtRepository.setText(repository.getName());

        ServiceGenerator.createService(GitHubService.class).getCommits(repository.getOwner().getLogin(), repository.getName()).enqueue(new Callback<List<RepositoryCommit>>() {
            @Override
            public void onResponse(Call<List<RepositoryCommit>> call, Response<List<RepositoryCommit>> response) {
                Toast.makeText(CommitsActivity.this, "adasd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RepositoryCommit>> call, Throwable t) {
                Toast.makeText(CommitsActivity.this, "adasd", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void finishActivity() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }
}