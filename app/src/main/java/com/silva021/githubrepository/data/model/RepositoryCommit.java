package com.silva021.githubrepository.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepositoryCommit {

    @SerializedName("commit")
    @Expose
    private Commit commit;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(RepositoryCommit commits) {
        this.commit = commit;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "commit=" + commit +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}';
    }

}
