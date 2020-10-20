package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.omahdilit.adapter.DetailModelAdapter;
import com.app.omahdilit.adapter.MoreModelAdapter;
import com.app.omahdilit.api.DetailModelApi;
import com.app.omahdilit.api.MoreModelApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.DetailModelItem;
import com.app.omahdilit.response.ModelItem;
import com.app.omahdilit.response.ModelResponse;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailModel extends AppCompatActivity {

    String idModel;

    @BindView(R.id.detail_model_nama)
    TextView detailModelNama;
    @BindView(R.id.detail_model_gaya)
    TextView detailModelGaya;
    @BindView(R.id.detail_model_detail)
    TextView detailModelDetail;
    @BindView(R.id.detail_model_foto)
    ViewPager detailModelFoto;
    @BindView(R.id.detail_model_kategori)
    TextView detailModelKategori;
    @BindView(R.id.detail_model_indicator)
    WormDotsIndicator detailModelIndicator;
    @BindView(R.id.detail_model_moremodel)
    RecyclerView detailModelMoremodel;

    DetailModelAdapter adapterFoto;
    MoreModelAdapter adapterMore;

    String[] uriFoto;
    List<ModelItem> modelItems;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_model);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarDetailModel);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idModel = bundle.getString("id");
        }

        loadingDialog = new LoadingDialog(DetailModel.this);
        loadingDialog.startLoading();

        adapterFoto = new DetailModelAdapter(DetailModel.this, uriFoto);
        adapterMore = new MoreModelAdapter(DetailModel.this,modelItems);

        DetailModelApi api = RetrofitApi.getDetailModel();
        Call<DetailModelItem> call = api.getDetailModel(idModel);
        call.enqueue(new Callback<DetailModelItem>() {
            @Override
            public void onResponse(Call<DetailModelItem> call, Response<DetailModelItem> response) {
                if (response.body()!=null){

                    uriFoto = new String[]{response.body().getPhoto1(), response.body().getPhoto2(), response.body().getPhoto3()};

                    detailModelNama.setText(response.body().getNamaModel());
                    detailModelGaya.setText(response.body().getJenisModel());
                    detailModelKategori.setText(response.body().getKategori());
                    detailModelDetail.setText(response.body().getDetail());

                    adapterFoto = new DetailModelAdapter(DetailModel.this, uriFoto);
                    adapterFoto.notifyDataSetChanged();
                    detailModelFoto.setAdapter(adapterFoto);
                    detailModelFoto.setPageMargin(148);
                    detailModelFoto.setClipToPadding(true);
                    detailModelIndicator.setViewPager(detailModelFoto);

                    loadingDialog.dismissLoading();
                }
            }

            @Override
            public void onFailure(Call<DetailModelItem> call, Throwable t) {

            }
        });

        MoreModelApi moreModelApi = RetrofitApi.getMoreModel();
        Call<ModelResponse> modelItemCall = moreModelApi.getMoreModel();
        modelItemCall.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.body()!=null){
                    modelItems = new ArrayList<>();
                    modelItems = response.body().getModelItems();

                    adapterMore = new MoreModelAdapter(DetailModel.this, modelItems);
                    adapterMore.notifyDataSetChanged();
                    detailModelMoremodel.setAdapter(adapterMore);
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {

            }
        });
    }
}