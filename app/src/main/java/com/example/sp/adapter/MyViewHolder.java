package com.example.sp.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.R;


public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView mUserName, mUserCountry;
    public ImageView mImage;

    public MyViewHolder(View itemView){
        super(itemView);
        mUserName = itemView.findViewById(R.id.userName);
        mUserCountry = itemView.findViewById(R.id.userCountry);
        mImage = itemView.findViewById(R.id.profileImage);
    }

}
