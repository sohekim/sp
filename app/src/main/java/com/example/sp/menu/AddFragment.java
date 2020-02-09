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
import android.widget.Toast;

import com.example.sp.R;
import com.example.sp.data.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    String TAG = "ADDFRAGMENT";
    EditText titleId, bodyId;
    String title, body;
    Button share;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // Inflate the layout for this fragment
        db = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        titleId = view.findViewById(R.id.title);
//        bodyId = view.findViewById(R.id.body);
        share = view.findViewById(R.id.share_btn);
        Log.d(TAG, "print sth");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "print sth2");
                createPost();
                titleId.setText("");
//                bodyId.setText("");
                Toast.makeText(getActivity(), "Your post have been uploaded!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    public void createPost() {

        Map<String, Object> post = new HashMap<>();
        post.put("uid", mFirebaseAuth.getUid() + 1);
        post.put("title", titleId.getText().toString());
        post.put("likes", 1);
//        post.put("story", bodyId.getText().toString());
//        post.put("same", bodyId.getText().toString());

        addPosts(post);
    }

    public void addPosts(Map<String, Object> post) {
        Log.d(TAG, "It works here");
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
