package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.omahdilit.adapter.AdapterCallback;
import com.app.omahdilit.adapter.PilihModelAdapter;
import com.app.omahdilit.adapter.PilihPromoAdapter;
import com.app.omahdilit.adapter.PromoAdapter;
import com.app.omahdilit.api.PromoApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.PromoItem;
import com.app.omahdilit.response.PromoResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihPromo extends AppCompatActivity implements AdapterCallback {

    @BindView(R.id.pilihPromo_rv_listpromo)
    RecyclerView recyclerView;

    @BindView(R.id.pilihpromo_layout_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.pilihpromo_button_remove)
    TextView btnRemove;

    LoadingDialog loadingDialog;

    List<PromoItem> promoItems;
    PilihPromoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_promo);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarPilihPromo);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        loadingDialog = new LoadingDialog(PilihPromo.this);

        Intent intent = getIntent();
        Integer idpromo = intent.getIntExtra("idpromo",0);
        if(idpromo!=0){
            btnRemove.setVisibility(View.VISIBLE);
        }

        promoItems = new ArrayList<>();
        adapter = new PilihPromoAdapter(PilihPromo.this, promoItems,PilihPromo.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getData() {

        loadingDialog.startLoading();
        PromoApi api = RetrofitApi.getApiPromo();
        Call<PromoResponse> call=api.getPromo();
        call.enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.body() != null){
                    promoItems = new ArrayList<>();
                    promoItems = response.body().getPromoItem();
                    adapter = new PilihPromoAdapter(PilihPromo.this, promoItems, PilihPromo.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PilihPromo.this));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PilihPromo.this, "error", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismissLoading();
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                Toast.makeText(PilihPromo.this, "Error " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCallback(Integer id) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id",id);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @OnClick(R.id.pilihpromo_button_remove) void  onRemoveClick(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("id",0);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}