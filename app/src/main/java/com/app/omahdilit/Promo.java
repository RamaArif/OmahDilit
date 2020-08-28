package com.app.omahdilit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.omahdilit.adapter.PromoAdapter;
import com.app.omahdilit.api.PromoApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.PromoItem;
import com.app.omahdilit.response.PromoResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Promo extends AppCompatActivity {

    @BindView(R.id.promo_rv_listPromo)
    RecyclerView rvPromo;
    @BindView(R.id.promo_layout_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    PromoAdapter promoAdapter;
    List<PromoItem> promoItems;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarPromo);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadingDialog = new LoadingDialog(Promo.this);

        promoItems = new ArrayList<>();
        promoAdapter = new PromoAdapter(Promo.this, promoItems);
        rvPromo.setHasFixedSize(true);
        rvPromo.setLayoutManager(new LinearLayoutManager(Promo.this));
        rvPromo.setAdapter(promoAdapter);

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    void getData(){
        loadingDialog.startLoading();
        PromoApi api = RetrofitApi.getApiPromo();
        Call<PromoResponse>call=api.getPromo();
        call.enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.body() != null){
                    promoItems = new ArrayList<>();
                    promoItems = response.body().getPromoItem();
                    promoAdapter = new PromoAdapter(Promo.this, promoItems);
                    rvPromo.setLayoutManager(new LinearLayoutManager(Promo.this));
                    rvPromo.setAdapter(promoAdapter);
                    promoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Promo.this, "error", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismissLoading();
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                Toast.makeText(Promo.this, "Error " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}