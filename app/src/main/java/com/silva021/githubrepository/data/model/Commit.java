package com.silva021.githubrepository.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("committer")
    @Expose
    private Committer committer;
    @SerializedName("message")
    @Expose
    private String message;

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "committer=" + committer +
                ", message='" + message + '\'' +
                '}';
    }
}
