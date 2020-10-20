package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;

import com.app.omahdilit.adapter.TukangListAdapter;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.api.TukangListApi;
import com.app.omahdilit.response.TukangListItem;
import com.app.omahdilit.response.TukangListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihTukangCukur extends AppCompatActivity {

    @BindView(R.id.rv_pilihtukang)
    RecyclerView rvTukangList;

    @BindView(R.id.toolbarPilihTukang)
    Toolbar toolbar;

    @BindView(R.id.pilihtukang_layout_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    List<TukangListItem> mResponse;

    TukangListAdapter tukangListAdapter;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tukang_cukur);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadingDialog = new LoadingDialog(PilihTukangCukur.this);

        mResponse = new ArrayList<>();
        tukangListAdapter = new TukangListAdapter(PilihTukangCukur.this, mResponse);
        rvTukangList.setHasFixedSize(true);
        rvTukangList.setLayoutManager(new LinearLayoutManager(PilihTukangCukur.this));
        rvTukangList.setAdapter(tukangListAdapter);

        getTukangList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTukangList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getTukangList(){
        loadingDialog.startLoading();
        TukangListApi api = RetrofitApi.getTukangList();
        Call<TukangListResponse> call = api.getTukangList();
        call.enqueue(new Callback<TukangListResponse>() {
            @Override
            public void onResponse(Call<TukangListResponse> call, Response<TukangListResponse> response) {
                if(response.body()!=null){
                    mResponse = new ArrayList<>();
                    mResponse = response.body().getTukangItems();
                    tukangListAdapter = new TukangListAdapter(PilihTukangCukur.this, mResponse);
                    rvTukangList.setLayoutManager(new LinearLayoutManager(PilihTukangCukur.this));
                    rvTukangList.setAdapter(tukangListAdapter);
                    tukangListAdapter.notifyDataSetChanged();
                    loadingDialog.dismissLoading();
                }
            }

            @Override
            public void onFailure(Call<TukangListResponse> call, Throwable t) {

            }
        });
    }
}