package com.example.sp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public TextView mComment;
    public ImageView mBullet;


    public CommentViewHolder(View itemView){
        super(itemView);
        mComment = itemView.findViewById(R.id.tvComment);
        mBullet = itemView.findViewById(R.id.ivBullet);
    }
}
