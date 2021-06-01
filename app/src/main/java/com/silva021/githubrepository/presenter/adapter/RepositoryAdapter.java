package com.silva021.githubrepository.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silva021.githubrepository.R;
import com.silva021.githubrepository.databinding.LayoutRepositoryBinding;
import com.silva021.githubrepository.data.model.*;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    List<Repository> mRepositoryList;
    Context mContext;
    private OnItemClickListener mListener;

    public RepositoryAdapter(List<Repository> repositoryList, Context mContext) {
        this.mRepositoryList = repositoryList;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
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
        holder.mBinding.txtStar.setText(repository.getStar());
        if (repository.getLanguage() != null) {
            holder.mBinding.txtLanguage.setText(repository.getLanguage());
        }
        holder.mBinding.txtOwner.setText(repository.getOwner().getLogin());
        if (repository.getDescription() != null)
            holder.mBinding.txtDescription.setText(repository.getDescription());

    }

    public Repository getRepository(int position) {
        return mRepositoryList.get(position);
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

            mBinding.constraintLayout.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(mBinding.getRoot(), getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, int position);

    }
}
