package com.example.androidhomework8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            //показать showDescriptions если первый раз
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_up_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id){
            case R.id.list_notes:
                FragmentList fragmentList = new FragmentList();
                fragmentTransaction.replace(R.id.container, fragmentList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                recreate();
                return true;
            case R.id.settings:
                FragmentSettings fragmentSettings = new FragmentSettings();
                fragmentTransaction.replace(R.id.container, fragmentSettings);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                recreate();
                return true;
            case R.id.about_notes:
                AboutFragment aboutFragment = new AboutFragment();
                fragmentTransaction.replace(R.id.container, aboutFragment).commit();
                fragmentTransaction.addToBackStack(null);
                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}