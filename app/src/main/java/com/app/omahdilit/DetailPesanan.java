package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.omahdilit.api.DetailModelApi;
import com.app.omahdilit.api.DetailTransaksiApi;
import com.app.omahdilit.api.DetailTukangApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.DetailModelItem;
import com.app.omahdilit.response.DetailTransaksiResponse;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesanan extends AppCompatActivity {

    @BindView(R.id.detailpesanan_text_tanggalpesanan)
    TextView textTanggalPesanan;
    @BindView(R.id.detailpesanan_text_kodepesanan)
    TextView textKodePesanan;
    @BindView(R.id.detailpesanan_text_namaTukangCukur)
    TextView textNamaTukang;
    @BindView(R.id.detailpesanan_image_tukangcukur)
    ImageView imgTukang;
    @BindView(R.id.detailpesanan_text_jaraktukangcukur)
    TextView textJarakTukang;
    @BindView(R.id.detailpesanan_text_alamat)
    TextView textAlamat;
    @BindView(R.id.detailpesanan_image_modelRambut)
    ImageView imgModel;
    @BindView(R.id.detailpesanan_text_namaModel)
    TextView textNamaModel;
    @BindView(R.id.detailpesanan_text_gayaRambut)
    TextView textGayaRambut;
    @BindView(R.id.detailpesanan_text_detailModelRambut)
    TextView textDetailModel;
    @BindView(R.id.detailpesanan_text_hargacukur)
    TextView textHargaCukur;
    @BindView(R.id.detailpesanan_text_hargapenanganan)
    TextView textHargaPenanganan;
    @BindView(R.id.detailpesanan_text_totalpotongan)
    TextView textPotongan;
    @BindView(R.id.detailpesanan_text_harga)
    TextView textHargaTotal;
    @BindView(R.id.detailpesanan_text_statusPesanan)
    TextView textStatus;
    @BindView(R.id.detailpesanan_layout_swipe)
    SwipeRefreshLayout layoutSwipe;
    @BindView(R.id.detailpesanan_button_whatsapp)
            TextView btnWhatsapp;


    String kodeTransaksi, idTukang, idModel, noTukang;
    Integer status;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarDetailPesanan);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            kodeTransaksi = intent.getStringExtra("kodeTransaksi");
        }

        loadingDialog = new LoadingDialog(DetailPesanan.this);

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
        loadingDialog.startLoading();
        DetailTransaksiApi api = RetrofitApi.getDetailTransaksi();
        Call<DetailTransaksiResponse> call = api.getDetailTransaksi(kodeTransaksi);
        call.enqueue(new Callback<DetailTransaksiResponse>() {
            @Override
            public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                if (response.body()!=null){
                    String inputDate = response.body().getCreatedAt();
                    Locale localeID = new Locale("id","ID");
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    Date date = new Date();
                    try {
                        date = inputFormat.parse(inputDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG, localeID);
                    textTanggalPesanan.setText(dateFormat.format(date));
                    textKodePesanan.setText(response.body().getCodeTransaksi());
                    idTukang = response.body().getIdtukang();
                    getDetailTukang();
                    textJarakTukang.setText(response.body().getJarak()+" Km dari Kamu");
                    textAlamat.setText(response.body().getAlamat());
                    idModel = response.body().getIdmodel();
                    getDetailModel();
                    int harga = response.body().getTotalharga() + response.body().getPromo();
                    textHargaCukur.setText(numberFormat.format(harga));
                    textPotongan.setText(numberFormat.format(response.body().getPromo()));
                    textHargaTotal.setText(numberFormat.format(response.body().getTotalharga()));
                    status = response.body().getStatus();
                    if (status==0){
                        textStatus.setText("Menunggu konfirmasi tukang cukur");
                        btnWhatsapp.setVisibility(View.GONE);
                    } else if (status==1){
                        textStatus.setText("Tukang cukur sedang menuju ke tempatmu");
                        btnWhatsapp.setVisibility(View.VISIBLE);
                    }
                    loadingDialog.dismissLoading();
                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.detailpesanan_button_whatsapp) void onWhatsappClick(){
        String url = "https://api.whatsapp.com/send?phone=+"+noTukang;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void getDetailModel() {
        DetailModelApi api = RetrofitApi.getDetailModel();
        Call<DetailModelItem> call = api.getDetailModel(String.valueOf(idModel));
        call.enqueue(new Callback<DetailModelItem>() {
            @Override
            public void onResponse(Call<DetailModelItem> call, Response<DetailModelItem> response) {
                if (response.body()!=null){
                    textNamaModel.setText(response.body().getNamaModel());
                    String urlImg = "http://omahdilit.my.id/public/images/" + response.body().getPhoto1();
                    Picasso.get().load(urlImg).fit().centerCrop().into(imgModel);
                    textGayaRambut.setText(response.body().getKategori());
                    textDetailModel.setText(response.body().getDetail());
                }
            }

            @Override
            public void onFailure(Call<DetailModelItem> call, Throwable t) {

            }
        });
    }

    private void getDetailTukang() {
    DetailTukangApi api = RetrofitApi.getDetailTukang();
    Call<DetailTukangResponse> call = api.getDetailTukang(idTukang);
    call.enqueue(new Callback<DetailTukangResponse>() {
        @Override
        public void onResponse(Call<DetailTukangResponse> call, Response<DetailTukangResponse> response) {
            String urlImg = "http://omahdilit.my.id/public/images/" + response.body().getPhoto();
            Picasso.get().load(urlImg).fit().centerCrop().into(imgTukang);
            textNamaTukang.setText(response.body().getName());
            noTukang = response.body().getNumber();
        }

        @Override
        public void onFailure(Call<DetailTukangResponse> call, Throwable t) {

        }
    });
    }

}