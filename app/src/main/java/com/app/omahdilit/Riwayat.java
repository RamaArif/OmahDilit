package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.app.omahdilit.adapter.RiwayatAdapter;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.api.RiwayatApi;
import com.app.omahdilit.response.RiwayatItem;
import com.app.omahdilit.response.RiwayatResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Riwayat extends AppCompatActivity {

    @BindView(R.id.riwayat_layout_swipe)
    SwipeRefreshLayout layoutSwipe;
    @BindView(R.id.riwayat_rv)
    RecyclerView rvRiwayat;

    List<RiwayatItem> riwayatItems;
    RiwayatAdapter adapter;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        Toolbar toolbar = findViewById(R.id.toolbarRiwayat);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadingDialog = new LoadingDialog(Riwayat.this);
        riwayatItems = new ArrayList<>();
        adapter = new RiwayatAdapter(Riwayat.this, riwayatItems);
        rvRiwayat.setHasFixedSize(true);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(Riwayat.this));
        rvRiwayat.setAdapter(adapter);

        getData();

        layoutSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                layoutSwipe.setRefreshing(false);
            }
        });

    }

    private void getData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        RiwayatApi api = RetrofitApi.getRiwayatList();
        Call<RiwayatResponse> call = api.getRiwayatList(user.getUid());
        call.enqueue(new Callback<RiwayatResponse>() {
            @Override
            public void onResponse(Call<RiwayatResponse> call, Response<RiwayatResponse> response) {
                if (response.body()!=null){
                    riwayatItems = new ArrayList<>();
                    riwayatItems = response.body().getBody();
                    adapter = new RiwayatAdapter(Riwayat.this, riwayatItems);
                    rvRiwayat.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<RiwayatResponse> call, Throwable t) {

            }
        });
    }
}