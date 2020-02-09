package com.example.sp.menu;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sp.PostActivity;
import com.example.sp.R;
import com.example.sp.data.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {


    FirebaseFirestore db;
    Post thisPost;
    String postId;
    String TAG = "PostFragment";
    ArrayList<String> commentsList;

    public PostFragment(String postId) {
        this.postId = postId;
        commentsList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_post, container, false);
        db = FirebaseFirestore.getInstance();
        readData(new FirestoreCallBack() {
            @Override
            public void onCallBack(Post post) {
                thisPost = post;
                setPostDisplay();
                Button addComment = view.findViewById(R.id.btnAddComment);
                addComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText etComm = view.findViewById(R.id.etComment);
                        addComment(etComm.getText().toString());
                    }
                });
            }
        });

        return view;
    }

    public interface FirestoreCallBack{
        void onCallBack(Post post);
    }

    private void setPostDisplay(){
        //Set UI component to the post accordingly
    }

    private void addComment(String comment){
        Map<String, Object> mapcomment = new HashMap<>();
        mapcomment.put("comment", comment);
        if(thisPost!=null){
            db.collection("posts").document(postId).collection("comments")
                    .add(mapcomment)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }

    private void getComments(){
        //RecyclerView for comments
        db.collection("posts")
                .document(postId).collection("comments").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot querySnapshot : task.getResult()) {
                        commentsList.add(querySnapshot.getString("comment"));
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void readData(final PostFragment.FirestoreCallBack firestoreCallBack){
        db.collection("posts")
                .document(postId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            String uid = document.getString("id");
                            String title = document.getString("title");
                            String story = document.getString("story");
                            String likes = document.getString("likes");
                            thisPost = new Post(document.getId(), uid, title, story, likes);
                            firestoreCallBack.onCallBack(thisPost);
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

}
