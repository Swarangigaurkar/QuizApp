package com.example.swarangigaurkar.quizzup;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private View mContentView;
    private  View mLoadingView;
    private int mShortAnimationDuration;
    public static String newString;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pos=getIntent().getExtras().getInt("pos");
        if(pos==1) {
            newString = getIntent().getExtras().getString("email");
        }

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer=findViewById(R.id.draw_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(MainActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new CardsShow()
        ).commit();
    }
    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new profile()
                ).commit();
                break;

            case R.id.cards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new CardsShow()
                ).commit();

                break;
            case R.id.nav_save:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new tutorials()
               ).commit();
                break;
            case R.id.aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new Aboutus()
                ).commit();
                break;

            case R.id.rateus:
               // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps")));
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                Toast.makeText(MainActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
