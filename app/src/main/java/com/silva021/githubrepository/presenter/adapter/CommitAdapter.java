package com.silva021.githubrepository.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silva021.githubrepository.databinding.LayoutCommitsBinding;
import com.silva021.githubrepository.data.model.Repository;
import com.silva021.githubrepository.data.model.RepositoryCommit;

import java.util.List;

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.ViewHolder> {

    List<RepositoryCommit> mRepositoryCommitsList;
    Context mContext;
    private OnItemClickListener mListener;

    public CommitAdapter(List<RepositoryCommit> mRepositoryCommitsList, Context mContext) {
        this.mRepositoryCommitsList = mRepositoryCommitsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutCommitsBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommitAdapter.ViewHolder holder, int position) {
        RepositoryCommit repositoryCommit = mRepositoryCommitsList.get(position);
        holder.mBinding.txtDate.setText(repositoryCommit.getCommit().getCommitter().getDate().replace("T", " ").replace("Z", ""));
        holder.mBinding.txtDescriptionCommit.setText(repositoryCommit.getCommit().getMessage());
    }

    @Override
    public int getItemCount() {
        return mRepositoryCommitsList.size();
    }

    public RepositoryCommit getRepositoryCommit(int position) {
        return mRepositoryCommitsList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutCommitsBinding mBinding;

        public ViewHolder(LayoutCommitsBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;

            mBinding.layoutCommit.setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
