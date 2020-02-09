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
import com.example.sp.menu.MainFragment;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    MainFragment mainFragment;
    ArrayList<Post> postArrayList;

    public MyAdapter(MainFragment mainFragment, ArrayList<Post> postArrayList){
        this.mainFragment = mainFragment;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainFragment.getActivity().getBaseContext());
        View view = layoutInflater.inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mTitle.setText(postArrayList.get(position).getTitle());
        holder.mLikeCount.setText(postArrayList.get(position).getLike());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAdapter.this.mainFragment.getActivity(), PostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("POST_ID", postArrayList.get(position).getPostId());
                mainFragment.startActivity(intent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

}
