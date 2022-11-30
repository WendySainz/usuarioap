package com.example.pibd;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class principal extends AppCompatActivity {

    //CONFIGURACION DEL MENU
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,favoriteFragment).commit();
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        return true;
                }

                return false;
            }
        });

    }
}