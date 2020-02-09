package com.example.sp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.PostActivity;
import com.example.sp.R;
import com.example.sp.data.Post;
import com.example.sp.menu.MainFragment;
import com.example.sp.menu.PostFragment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    PostFragment postFragment;
    ArrayList<String> commentArrayList;

    public CommentAdapter(PostFragment postFragment, ArrayList<String> commentArrayList){
        this.postFragment = postFragment;
        this.commentArrayList = commentArrayList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(postFragment.getActivity().getBaseContext());
        View view = layoutInflater.inflate(R.layout.comment_layout, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.mComment.setText(commentArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

}


