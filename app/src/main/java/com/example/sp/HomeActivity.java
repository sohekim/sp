package com.example.sp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.sp.menu.AddFragment;
import com.example.sp.menu.MainFragment;
import com.example.sp.menu.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView navigation;
    FrameLayout frameLayout;

    private MainFragment mainFragment;
    private ProfileFragment profileFragemnt;
    private AddFragment addFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

}
