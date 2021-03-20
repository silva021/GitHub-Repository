package com.silva021.githubrepository.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silva021.githubrepository.R;
import com.silva021.githubrepository.databinding.LayoutRepositoryBinding;
import com.silva021.githubrepository.model.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    List<Repository> mRepositoryList;
    Context mContext;

    public RepositoryAdapter(List<Repository> repositoryList, Context mContext) {
        this.mRepositoryList = repositoryList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        return new ViewHolder(LayoutRepositoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryAdapter.ViewHolder holder, int position) {
        Repository repository = mRepositoryList.get(position);

        Glide.with(mContext)
                .load(repository.getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_github)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.mBinding.imgProfilePhoto);

        holder.mBinding.txtNameRepository.setText(repository.getName());
        holder.mBinding.txtLanguage.setText(repository.getLanguage());
        holder.mBinding.txtOwner.setText(repository.getOwner().getLogin());
        if  (repository.getDescription() != null)
            holder.mBinding.txtDescription.setText(repository.getDescription());

    }

    @Override
    public int getItemCount() {
        return mRepositoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LayoutRepositoryBinding mBinding;

        public ViewHolder(LayoutRepositoryBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
