package com.example.android_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new LoginFragment()).commit();

        // Hide nav bar on launch
        // findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        if(itemId == R.id.algorithm) selectedFragment = new WorkoutFragment();
        else if (itemId == R.id.course) selectedFragment = new DietFragment();
        else if (itemId == R.id.profile) selectedFragment = new profile();

        if (selectedFragment != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

        return true;
    };
}