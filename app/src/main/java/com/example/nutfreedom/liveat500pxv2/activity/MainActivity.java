package com.example.nutfreedom.liveat500pxv2.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.nutfreedom.liveat500pxv2.R;
import com.example.nutfreedom.liveat500pxv2.dao.PhotoItemDao;
import com.example.nutfreedom.liveat500pxv2.databinding.ActivityMainBinding;
import com.example.nutfreedom.liveat500pxv2.fragment.MainFragment;
import com.example.nutfreedom.liveat500pxv2.fragment.MoreInfoFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {


    ActionBarDrawerToggle actionBarDrawerToggle;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        setSupportActionBar(binding.toolBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPhotoItemClicked(PhotoItemDao dao) {
        FrameLayout moreInfoContainer = (FrameLayout) findViewById(R.id.moreInfoContainer);
        if (moreInfoContainer == null) {
            Intent intent = new Intent(MainActivity.this, MoreInfoActivity.class);
            intent.putExtra("dao", dao);
            startActivity(intent);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer, MoreInfoFragment.newInstance(dao))
                    .commit();
        }
    }

}
