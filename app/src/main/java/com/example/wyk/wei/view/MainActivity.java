package com.example.wyk.wei.view;

import android.content.Intent;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.wyk.wei.R;
import com.example.wyk.wei.fragment.FourFragment;
import com.example.wyk.wei.fragment.RecyclerViewFragment;
import com.example.wyk.wei.fragment.ToDoListFragment;
import com.example.wyk.wei.fragment.WaterfallFlowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationBar bottomNavigationBar;
    TextBadgeItem textBadgeItem;
    ShapeBadgeItem shapeBadgeItem;

    FloatingActionButton fab;
    Toolbar toolbar;

    List<Fragment> fragmentList;

    Fragment recyclerviewFragment;
    Fragment waterfallFlowFragment;
    Fragment todoListFragment;
    Fragment fourFragment;

    Fragment currentFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    View contentView;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        bottomNavigationBar = findViewById(R.id.wei_content_bottom_navigation_bar);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        recyclerviewFragment = new RecyclerViewFragment();
        waterfallFlowFragment = new WaterfallFlowFragment();
        todoListFragment = new ToDoListFragment();
        fourFragment = new FourFragment();

        fragmentList = new ArrayList<>();
        addFragment();
        initBottomNavigationBar();

    }
    private void initBottomNavigationBar(){
        textBadgeItem = new TextBadgeItem();
        shapeBadgeItem = new ShapeBadgeItem();

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor(R.color.colorWhite);
        bottomNavigationBar
                .setActiveColor(R.color.colorPrimary)
                .setInActiveColor(R.color.colorGrey);

        textBadgeItem
                .setBackgroundColor(R.color.colorRed)
                .setText("99")
                .setHideOnSelect(true);
        shapeBadgeItem
                .setShapeColorResource(R.color.colorRed)
                .setGravity(Gravity.TOP|Gravity.END)
                .setHideOnSelect(true);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.wei_bottom_armchair,"柴")
                .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.wei_bottom_bench))
                .setBadgeItem(textBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.wei_bottom_bed,"米")
                .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.wei_bottom_bunk_bed))
                .setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.wei_bottom_couch,"油")
                .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.wei_bottom_dressing_table)))
                .addItem(new BottomNavigationItem(R.drawable.wei_bottom_home_bar,"盐")
                .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.drawable.wei_bottom_kitchen_cabinet)))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    private void addFragment(){

        fragmentList.add(recyclerviewFragment);
        fragmentList.add(waterfallFlowFragment);
        fragmentList.add(todoListFragment);
        fragmentList.add(fourFragment);

    }

    private void showFragment(int page){
        if (currentFragment!=null){
            fragmentTransaction.hide(currentFragment);
        }
        currentFragment = fragmentList.get(page);
        if (currentFragment.isAdded()){
            fragmentTransaction.show(currentFragment);
        }else{
            fragmentTransaction.add(R.id.wei_content_frame_layout, currentFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_login) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
