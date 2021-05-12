package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
    public void openActivity(View v)
    {
        Intent intent_gallery = new Intent(MainActivity.this, MainActivity4.class);
        startActivity(intent_gallery);

    }
    public void openActivity2(View v)
    {
        Intent intent_feature = new Intent(MainActivity.this, ActivityFeatured.class);
        startActivity(intent_feature);
    }

    public void cust_logout(View v)
    {
        Intent clogout = new Intent(MainActivity.this, MainActivity_First.class);

        clogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(clogout);
    }
}