package com.app.omahdilit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.app.omahdilit.adapter.ModelRambutAdapter;
import com.app.omahdilit.adapter.PromoAdapter;
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

public class ModelRambut extends AppCompatActivity {

    @BindView(R.id.rv_model_gridmodel)
    RecyclerView model_rv_gridmodel;
    @BindView(R.id.model_layout_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    ModelRambutAdapter modelRambutAdapter;
    List<ModelItem> modelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_rambut);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarModelRambut);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        modelItems = new ArrayList<>();
        modelRambutAdapter = new ModelRambutAdapter(ModelRambut.this, modelItems);
        model_rv_gridmodel.setHasFixedSize(true);
        model_rv_gridmodel.setAdapter(modelRambutAdapter);

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void getData(){

        ModelApi modelApi = RetrofitApi.getApiModel();
        Call<ModelResponse> call = modelApi.getModel();
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.body() != null){
                    modelItems = new ArrayList<>();
                    modelItems = response.body().getModelItems();
                    modelRambutAdapter = new ModelRambutAdapter(ModelRambut.this, modelItems);
                    model_rv_gridmodel.setAdapter(modelRambutAdapter);
                    modelRambutAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ModelRambut.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(ModelRambut.this, "error : "+ t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}