package com.silva021.githubrepository.presenter.ui.login;

import androidx.lifecycle.LiveData;

import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.User;

import androidx.lifecycle.ViewModel;

import com.silva021.githubrepository.domain.repository.GitHubRepository;

import java.util.List;

public class LoginViewModel extends ViewModel {
    private GitHubRepository gitHubRepository = new GitHubRepository();

    public LiveData<User> verifyUser(String user) {
        return gitHubRepository.verifyUser(user);
    }
}
