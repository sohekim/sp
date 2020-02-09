package com.example.sp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.sp.data.Post;
import com.example.sp.menu.AddFragment;
import com.example.sp.menu.MainFragment;
import com.example.sp.menu.PostFragment;
import com.example.sp.menu.ProfileFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;


public class PostActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    FrameLayout frameLayout;

    private MainFragment mainFragment;
    private PostFragment postFragment;
    private ProfileFragment profileFragemnt;
    private AddFragment addFragment;

    FirebaseFirestore db;
    Post thisPost;
    String TAG = "PostActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
//        db = FirebaseFirestore.getInstance();
//        readData(new FirestoreCallBack() {
//            @Override
//            public void onCallBack(Post post) {
//                thisPost = post;
//                setPostDisplay();
//                final Button addComment = findViewById(R.id.btAddComment);
//                addComment.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        EditText etComm = findViewById(R.id.etComment);
//                        addComment(etComm.getText().toString());
//                    }
//                });
//            }
//        });

        navigation = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);
        String postId = (String) getIntent().getSerializableExtra("POST_ID");

        postFragment = new PostFragment(postId);
        mainFragment = new MainFragment();
        profileFragemnt = new ProfileFragment();
        addFragment = new AddFragment();

        InitializeFragment(postFragment);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_Home:
                        InitializeFragment(mainFragment);
                        return true;

                    case R.id.navigation_profile:
                        InitializeFragment(profileFragemnt);
                        return true;

                    case R.id.navigation_add:
                        InitializeFragment(addFragment );
                        return true;
                }
                return false;
            }
        });
    }

    public void InitializeFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.postFrameLayout, fragment);
        fragmentTransaction.commit();
    }
//
//    public interface FirestoreCallBack{
//        void onCallBack(Post post);
//    }
//
//    private void setPostDisplay(){
//        //Set UI component to the post accordingly
//    }
//
//    private void addComment(String comment){
//        Map<String, Object> mapcomment = new HashMap<>();
//        mapcomment.put("comment", comment);
//        if(thisPost!=null){
//            db.collection("posts").document("testPostID").collection("comments")
//                    .add(mapcomment)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });
//        }
//    }
//
//    private void getComments(){
//
//    }
//    private void readData(final FirestoreCallBack firestoreCallBack){
//        String postId = (String) getIntent().getSerializableExtra("POST_ID");
//
//        db.collection("posts")
//                .document("testPostID").get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            DocumentSnapshot document = task.getResult();
//                            String postId = "postID";
//                            String uid = document.getString("id");
//                            String title = document.getString("title");
//                            String story = document.getString("story");
//                            String likes = document.getString("likes");
//                            thisPost = new Post(document.getId(), uid, title, story, likes);
//                            firestoreCallBack.onCallBack(thisPost);
//                        } else {
//                            Log.d(TAG, "get failed with ", task.getException());
//                        }
//                    }
//                });
//    }

}

