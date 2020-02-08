package com.example.sp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sp.data.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    FirebaseFirestore db;
    Post thisPost;
    String TAG = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        db = FirebaseFirestore.getInstance();
        readData(new FirestoreCallBack() {
            @Override
            public void onCallBack(Post post) {
                thisPost = post;
                setPostDisplay();
                Log.d(TAG, thisPost.getId().toString());
            }
        });
        
    }
    private interface FirestoreCallBack{
        void onCallBack(Post post);
    }

    private void setPostDisplay(){
        //Set UI component to the post accordingly
    }

    private void addComment(String comment){
        comment = "testing comments";
        Map<String, Object> mapcomment = new HashMap<>();
        mapcomment.put("comment", comment);
        if(thisPost!=null){
            db.collection("posts").document("testPostID").collection("comments")
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

    }
    private void readData(final FirestoreCallBack firestoreCallBack){
        String postId = (String) getIntent().getSerializableExtra("POST_ID");

        db.collection("posts")
                .document("testPostID").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            String postId = "postID";
                            String uid = (String) document.getString("id");
                            String title = (String) document.getString("title");
                            String story = (String) document.getString("story");
                            long likes = 2;
                            long same = 3;
                            thisPost = new Post(postId, uid, title, story, likes, same);
                            firestoreCallBack.onCallBack(thisPost);
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }
}
