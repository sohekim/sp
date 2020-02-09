package com.example.sp.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.R;


public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView mTitle,mLikeCount;
    public ImageView mLikeView;


    public MyViewHolder(View itemView){
        super(itemView);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mLikeCount = itemView.findViewById(R.id.tvCount);
        mLikeView = itemView.findViewById(R.id.ivLike);

    }

}
