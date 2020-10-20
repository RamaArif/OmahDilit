package com.app.omahdilit;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.omahdilit.api.CekTransaksiApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.CekTransaksiResponse;
import com.app.omahdilit.ui.home.HomeFragment;
import com.app.omahdilit.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener{

    @BindView(R.id.bnve)
    BottomNavigationView bnve;
    @BindView(R.id.fab_cukur)
    FloatingActionButton fabParking;

    ViewDialog alertDialog ;


    Fragment fragment1  = new HomeFragment();
    Fragment fragment3  = new ProfileFragment();
    Fragment active     = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        alertDialog = new ViewDialog();

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        CekTransaksiApi api1 = RetrofitApi.cekTransaksi();
        Call<CekTransaksiResponse> call1 = api1.cekTransaksi(user.getUid());
        call1.enqueue(new Callback<CekTransaksiResponse>() {
            @Override
            public void onResponse(Call<CekTransaksiResponse> call, Response<CekTransaksiResponse> response) {
                if (response.body().getStatus()==null){
                    Intent intent = new Intent(MainActivity.this, PilihTukangCukur.class);
                    startActivity(intent);
                } else{
                    alertDialog.showDialog(MainActivity.this, R.string.textErrorOrderAktif);
                }
            }

            @Override
            public void onFailure(Call<CekTransaksiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = fragment1;
                break;
            case R.id.navigation_profile:
                fragment = fragment3;
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}