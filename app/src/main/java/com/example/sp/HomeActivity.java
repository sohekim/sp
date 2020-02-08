package com.example.sp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sp.data.Post;
import com.example.sp.data.Profile;
import com.example.sp.menu.AddFragment;
import com.example.sp.menu.MainFragment;
import com.example.sp.menu.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //     Profile thisUser;
    String TAG = "MainActivity";
    BottomNavigationView navigation;
    FrameLayout frameLayout;

    private MainFragment mainFragment;
    private ProfileFragment profileFragemnt;
    private AddFragment addFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
//        btnLogout = findViewById(R.id.button2);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(intToMain);
//            }
//        });

        navigation = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayout);

        mainFragment = new MainFragment();
        profileFragemnt = new ProfileFragment();
        addFragment = new AddFragment();

        InitializeFragment(mainFragment);

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
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void createPost() {
        //Read from UI components and create Post object
        //Pass post object to addPost(postObject) to be added into the database

        final Post thisPost = new Post(mFirebaseAuth.getUid(), "My Title", "My Story blah blah blah", 10, 20);

        Map<String, Object> post = new HashMap<>();
        post.put("uid", thisPost.getId());
        post.put("title", thisPost.getTitle());
        post.put("story", thisPost.getStory());
        post.put("likes", thisPost.getLike());
        post.put("same", thisPost.getSame());

        addPosts(post);

    }

    public void addPosts(Map<String, Object> post) {
        db.collection("posts")
                .add(post)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
//                         thisUser.addPostID(documentReference.getId());
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
