package com.silva021.githubrepository.data.api;

import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.RepositoryCommit;
import com.silva021.githubrepository.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}")
    Call<User> getUser(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repository>> getRepositories(@Path("user") String user);

    @GET("repos/{user}/{repository}/commits")
    Call<List<RepositoryCommit>> getCommits(@Path("user") String user, @Path("repository") String repository);


}
