package com.example.audicoastoverdrivedirector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.audicoastoverdrivedirector.fragment.CategoriesFragment;
import com.example.audicoastoverdrivedirector.fragment.HomeFragment;
import com.example.audicoastoverdrivedirector.fragment.InternalFragment;
import com.google.android.material.navigation.NavigationView;
import com.example.audicoastoverdrivedirector.R;

public class FileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open_Drawer,R.string.Close_Drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i;

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
                break;

            case R.id.nav_categories:
                CategoriesFragment categoriesFragment = new CategoriesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, categoriesFragment).addToBackStack(null).commit();
                break;
            case R.id.nav_internal:
                InternalFragment internalFragment = new InternalFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,internalFragment).addToBackStack(null).commit();
                break;
            case R.id.nav_createFolder:
                i = new Intent(FileActivity.this, CreateFolder.class);
                startActivity(i);
                break;
            case R.id.nav_about:

                i = new Intent(FileActivity.this, about.class);
                startActivity(i);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        getSupportFragmentManager().popBackStackImmediate();
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }



}