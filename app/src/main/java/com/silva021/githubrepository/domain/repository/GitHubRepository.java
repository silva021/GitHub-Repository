package com.silva021.githubrepository.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.silva021.githubrepository.data.api.GitHubService;
import com.silva021.githubrepository.data.api.ServiceGenerator;
import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.RepositoryCommit;
import com.silva021.githubrepository.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubRepository {
    public LiveData<User> verifyUser(String user) {
        final MutableLiveData<User> data = new MutableLiveData<User>();
        ServiceGenerator.createService(GitHubService.class).getUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<Repository>> getListOfRepository(String username) {
        final MutableLiveData<List<Repository>> data = new MutableLiveData<>();
        ServiceGenerator.createService(GitHubService.class).getRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<RepositoryCommit>> getListOfCommits(String username, String repository) {
        final MutableLiveData<List<RepositoryCommit>> data = new MutableLiveData<>();
        ServiceGenerator.createService(GitHubService.class).getCommits(username, repository).enqueue(new Callback<List<RepositoryCommit>>() {
            @Override
            public void onResponse(Call<List<RepositoryCommit>> call, Response<List<RepositoryCommit>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RepositoryCommit>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


}
