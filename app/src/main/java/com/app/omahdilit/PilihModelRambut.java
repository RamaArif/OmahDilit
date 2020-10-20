package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.omahdilit.adapter.AdapterCallback;
import com.app.omahdilit.adapter.ModelRambutAdapter;
import com.app.omahdilit.adapter.PilihModelAdapter;
import com.app.omahdilit.api.ModelApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.ModelItem;
import com.app.omahdilit.response.ModelResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihModelRambut extends AppCompatActivity implements AdapterCallback {

    @BindView(R.id.rv_pilihmodel_grid)
    RecyclerView recyclerView;

    @BindView(R.id.pilihmodel_layout_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    List<ModelItem> modelItems;
    PilihModelAdapter adapter;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_model_rambut);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarPilihModelRambut);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
//                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        loadingDialog = new LoadingDialog(PilihModelRambut.this);

        modelItems = new ArrayList<>();
        adapter = new PilihModelAdapter(PilihModelRambut.this, modelItems,PilihModelRambut.this);
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
        ModelApi modelApi = RetrofitApi.getApiModel();
        Call<ModelResponse> call = modelApi.getModel();
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.body() != null){
                    modelItems = new ArrayList<>();
                    modelItems = response.body().getModelItems();
                    adapter = new PilihModelAdapter(PilihModelRambut.this, modelItems,PilihModelRambut.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PilihModelRambut.this, "error", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismissLoading();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(PilihModelRambut.this, "error : "+ t, Toast.LENGTH_SHORT).show();
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
}