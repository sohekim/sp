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

import com.example.sp.HomeActivity;
import com.example.sp.PostActivity;
import com.example.sp.R;
import com.example.sp.data.Post;
import com.example.sp.menu.MainFragment;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

//    private DatabaseReference mDatabase;
//    mDatabase = FirebaseDatabase.getInstance().getReference();
    MainFragment mainFragment;
    ArrayList<Post> postArrayList;
//    Typeface font = ResourcesCompat.getFont(this, R.font.yeseva_one);

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
        Drawable d = ResourcesCompat.getDrawable(holder.itemView.getContext().getResources(), R.drawable.ic_favorite_black_24dp, null);
        holder.mLikeView.setImageDrawable(d);
        holder.mTitle.setText(postArrayList.get(position).getTitle());
        Typeface typeface = ResourcesCompat.getFont(holder.itemView.getContext(), R.font.yeseva_one);
        holder.mTitle.setTypeface(typeface);
        holder.mLikeCount.setText(postArrayList.get(position).getLike());
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAdapter.this.mainFragment.getActivity(), PostActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("POST_ID", postArrayList.get(position).getPostId());
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

    public void addHeart(@NonNull MyViewHolder holder) {
       int num = Integer.parseInt(holder.mLikeCount.getText().toString());
       holder.mLikeCount.setText(num+1);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

}
