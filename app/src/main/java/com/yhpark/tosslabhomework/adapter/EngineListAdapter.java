package com.yhpark.tosslabhomework.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhpark.tosslabhomework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ppyh0 on 2017-05-14.
 */

public class EngineListAdapter extends ArrayAdapter<Integer> {

    public EngineListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Integer[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(getContext());
        }

        ((ImageView) convertView).setImageResource(getItem(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_engine, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivIcon.setImageResource(getItem(position));
        holder.tvName.setText(
                getItem(position).equals(R.drawable.naver) ? "Naver" : "Daum"
        );
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


