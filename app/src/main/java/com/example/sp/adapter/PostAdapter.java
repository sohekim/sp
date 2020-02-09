package com.example.sp.adapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sp.PostActivity;
import com.example.sp.R;
import com.example.sp.data.Post;
import com.example.sp.menu.MainFragment;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

//    private DatabaseReference mDatabase;
//    mDatabase = FirebaseDatabase.getInstance().getReference();
    MainFragment mainFragment;
    ArrayList<Post> postArrayList;
//    Typeface font = ResourcesCompat.getFont(this, R.font.yeseva_one);

    public PostAdapter(MainFragment mainFragment, ArrayList<Post> postArrayList){
        this.mainFragment = mainFragment;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainFragment.getActivity().getBaseContext());
        View view = layoutInflater.inflate(R.layout.card_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final int safePosition = position;

        holder.mTitle.setText(postArrayList.get(position).getTitle());
        Typeface typeface = ResourcesCompat.getFont(holder.itemView.getContext(), R.font.yeseva_one);
        holder.mTitle.setTypeface(typeface);
        holder.mLikeCount.setText(postArrayList.get(position).getLike());
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostAdapter.this.mainFragment.getActivity(), PostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("POST_ID", postArrayList.get(safePosition).getPostId());
                mainFragment.startActivity(intent);
            }

        });

        holder.mLikeView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // add number
            }

        });
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

}
