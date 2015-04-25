package com.ps.citizen3.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ps.citizen3.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public class MainActivity extends ActionBarActivity implements DrawerRecyclerView.NavigationDrawerCallbacks {
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.drawer_recyclerView) DrawerRecyclerView drawerRecyclerView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.d("onCreate");
        ButterKnife.inject(this);

        // Set toolbar as actionbar
        toolbar.setBackgroundColor(Color.parseColor("#455A64"));
        setSupportActionBar(toolbar);

        // Set status bar color - Lollipop exclusive
        drawerLayout.setStatusBarBackgroundColor(Color.parseColor("#263238"));

        // Add action bar toggle
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Setup drawer RecyclerView
        drawerRecyclerView.setUp(this, drawerLayout);

        // Set starting fragment
        switchFragment(RepFragment.newInstance());
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this, "The Item Clicked is: " + position, Toast.LENGTH_SHORT).show();
    }
}
