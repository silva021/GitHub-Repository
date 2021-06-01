package com.silva021.githubrepository.presenter.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.domain.repository.GitHubRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private GitHubRepository gitHubRepository = new GitHubRepository();

    public LiveData<List<Repository>> getListOfRepository(String user) {
        return gitHubRepository.getListOfRepository(user);
    }
}
