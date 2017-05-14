package com.yhpark.tosslabhomework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yhpark.tosslabhomework.R;
import com.yhpark.tosslabhomework.model.SearchResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.SearchResultHolder> {
    Context context;
    List<SearchResult> items;

    public ImageListAdapter(Context context, List<SearchResult> items) {
        this.context = context;
        this.items = items;
    }

    static class SearchResultHolder extends RecyclerView.ViewHolder {
        private View itemView;

        public View getItemView() {
            return itemView;
        }

        @BindView(R.id.ivResult)
        public ImageView ivResult;

        @BindView(R.id.progress)
        public ProgressBar progress;

        public SearchResultHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public SearchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchResultHolder(LayoutInflater.from(context).inflate(R.layout.row_result, parent, false));
    }

    @Override
    public void onBindViewHolder(final SearchResultHolder holder, int position) {
        holder.progress.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(items.get(position).getImg_url())
                .thumbnail(0.1f)
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.progress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.ivResult);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addAll(List<SearchResult> imageUrls) {
        this.items = imageUrls;
        notifyDataSetChanged();
    }
}
