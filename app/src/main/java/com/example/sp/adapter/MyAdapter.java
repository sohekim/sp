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
import com.example.sp.adapter.MyViewHolder;
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
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mUserName.setText(postArrayList.get(position).getTitle());
        holder.mUserCountry.setText(postArrayList.get(position).getStory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAdapter.this.homeActivity, PostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("TITLE", postArrayList.get(position).getTitle());
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
