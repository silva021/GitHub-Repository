package com.silva021.githubrepository.presenter.ui.commit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.silva021.githubrepository.presenter.adapter.CommitAdapter;
import com.silva021.githubrepository.data.api.GitHubService;
import com.silva021.githubrepository.data.api.ServiceGenerator;
import com.silva021.githubrepository.databinding.ActivityCommitsBinding;
import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.RepositoryCommit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitsActivity extends AppCompatActivity implements CommitAdapter.OnItemClickListener {
    private Repository mRepository;
    private ActivityCommitsBinding mBinding;
    private CommitsViewModel commitsViewModel;
    private CommitAdapter mCommitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCommitsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        commitsViewModel = new ViewModelProvider(this).get(CommitsViewModel.class);

        if (getIntent().getExtras() != null) {
            mRepository = (Repository) getIntent().getExtras().getSerializable("object");
            updateView(mRepository);
        }

        mBinding.imgBack.setOnClickListener(view -> finishActivity());
    }

    private void updateView(Repository repository) {
        mBinding.txtRepository.setText(repository.getName());
        commitsViewModel.getListOfRepository(repository.getOwner().getLogin(), repository.getName()).observe(this, this::initRecycler);
    }

    private void finishActivity() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initRecycler(List<RepositoryCommit> list) {
        mCommitAdapter = new CommitAdapter(list, getApplicationContext());
        mCommitAdapter.setOnItemClickListener(this);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBinding.recyclerView.setAdapter(mCommitAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    @Override
    public void onItemClick(int position) {
        RepositoryCommit repositoryCommit = mCommitAdapter.getRepositoryCommit(position);
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(repositoryCommit.getHtmlUrl())));
    }
}