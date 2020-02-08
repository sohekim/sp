package com.example.sp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.HomeActivity;
import com.example.sp.PostActivity;
import com.example.sp.R;
import com.example.sp.data.Post;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    HomeActivity homeActivity;
    ArrayList<Post> postArrayList;

    public MyAdapter(HomeActivity homeActivity, ArrayList<Post> postArrayList){
        this.homeActivity = homeActivity;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(homeActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.post_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mTitle.setText(postArrayList.get(position).getTitle());
        holder.mLikeCount.setText(postArrayList.get(position).getLike()+"");
        holder.mSameCount.setText(postArrayList.get(position).getSame()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAdapter.this.homeActivity, PostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("TITLE", postArrayList.get(position).getId());
                homeActivity.startActivity(intent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
