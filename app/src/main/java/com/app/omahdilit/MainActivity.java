package com.app.omahdilit;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.omahdilit.ui.home.HomeFragment;
import com.app.omahdilit.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener{

    @BindView(R.id.bnve)
    BottomNavigationView bnve;
    @BindView(R.id.fab_cukur)
    FloatingActionButton fabParking;

    Fragment fragment1  = new HomeFragment();
    Fragment fragment3  = new ProfileFragment();
    Fragment active     = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, fragment1)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, fragment3).hide(fragment3)
                .commit();

        bnve.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().hide(active).show(fragment).commit();
//            getSupportFragmentManager().beginTransaction().detach(active).attach(fragment).commit();
            active = fragment;
            return true;
        }
        return true;
    }

    @OnClick({R.id.fab_cukur}) void fab(){
        Intent intent = new Intent(MainActivity.this, PilihTukangCukur.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                loadFragment(fragment1);

                break;
            case R.id.navigation_profile:
                loadFragment(fragment3);

                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}