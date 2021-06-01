package com.silva021.githubrepository.presenter.ui.commit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.RepositoryCommit;
import com.silva021.githubrepository.domain.repository.GitHubRepository;

import java.util.List;

public class CommitsViewModel extends ViewModel {
    private GitHubRepository gitHubRepository = new GitHubRepository();

    public LiveData<List<RepositoryCommit>> getListOfRepository(String user, String repository) {
        return gitHubRepository.getListOfCommits(user, repository);
    }
}
