 package com.example.sp;

 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;

 import com.example.sp.data.Post;
 import com.example.sp.data.Profile;
 import com.google.android.gms.tasks.OnFailureListener;
 import com.google.android.gms.tasks.OnSuccessListener;
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
     Profile thisUser;
     String TAG = "MainActivity";


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        thisUser = new Profile("UID", "Ahona", "androidas@hotmail.com", "Seoul", "Seoul");
        btnLogout = findViewById(R.id.button2);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 FirebaseAuth.getInstance().signOut();
                 Intent intToMain = new Intent (HomeActivity.this, MainActivity.class);
                 startActivity(intToMain);
            }
        });
    }
}
