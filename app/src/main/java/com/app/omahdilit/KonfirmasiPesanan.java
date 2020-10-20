package com.app.omahdilit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.app.omahdilit.api.CustomerApi;
import com.app.omahdilit.api.DetailModelApi;
import com.app.omahdilit.api.DetailPromoApi;
import com.app.omahdilit.api.HargaApi;
import com.app.omahdilit.api.JarakTukangApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.api.TransaksiApi;
import com.app.omahdilit.response.BaseResponse;
import com.app.omahdilit.response.CustomerResponse;
import com.app.omahdilit.response.DetailModelItem;
import com.app.omahdilit.response.DetailPromoItem;
import com.app.omahdilit.response.HargaResponse;
import com.app.omahdilit.response.JarakResponse;
import com.app.omahdilit.response.TransaksiResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiPesanan extends AppCompatActivity{

    @BindView(R.id.konfirmasipesanan_text_namauser)
    TextView textNamaUser;
    @BindView(R.id.konfirmasipesanan_text_nohp)
    TextView textNoHpUser;
    @BindView(R.id.konfirmasipesanan_input_alamat)
    TextView inputAlamat;
    @BindView(R.id.konfirmasipesanan_image_tukangcukur)
    ImageView imageTukang;
    @BindView(R.id.konfirmasipesanan_text_namaTukangCukur)
    TextView textNamaTukang;
    @BindView(R.id.konfirmasipesanan_text_jaraktukangcukur)
    TextView textJarakTukang;
    @BindView(R.id.layout2)
    ConstraintLayout layoutTukang;
    @BindView(R.id.konfirmasipesanan_text_harga)
    TextView textHarga;
    @BindView(R.id.konfirmasipesanan_button_pilihModelRambut)
    CardView layoutPilihModel;
    @BindView(R.id.konfirmasipesanan_layout_modelRambutDipilih)
    ConstraintLayout layoutModelRambut;
    @BindView(R.id.konfirmasipesanan_image_modelRambut)
    ImageView imageModelRambut;
    @BindView(R.id.detailpesanan_text_namaModel)
    TextView textNamaModel;
    @BindView(R.id.konfirmasipesanan_text_gayaRambut)
    TextView textGayaRambut;
    @BindView(R.id.konfirmasipesanan_text_modelRambut)
    TextView textDetailModelRambut;
    @BindView(R.id.konfirmasipesanan_button_repickModelRambut)
    ImageView btnRepickModel;
    @BindView(R.id.konfirmasipesanan_text_potongan)
    TextView textPotongan;
    @BindView(R.id.konfirmasipesanan_text_hargacukur)
    TextView textHargaCukur;
    @BindView(R.id.konfirmasipesanan_text_hargapenanganan)
    TextView textHargaPenanganan;
    @BindView(R.id.konfirmasipesanan_text_totalpotongan)
    TextView textTotalPotongan;
    @BindView(R.id.konfirmasipesanan_button_pilihMetodePembayaran)
    ConstraintLayout layoutMetodePembayaran;

    Integer idTukang, idCustomer, idModelRambut, idPromo=0, jarak, hargatotal, potongantotal;
    String namaTukang, photoTukang, uidCustomer, alamat;
    double lat1, long1, lat2,long2;

    private static int AUTOCOMPLETE_REQUEST_CODE = 2;

    FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseAuth mAuth;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pesanan);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarKonfirmasiPesanan);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loadingDialog = new LoadingDialog(KonfirmasiPesanan.this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uidCustomer = user.getUid();

        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyDWxy8MciL_TIacdS1aPDKDaKCDZI9invA");

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            idTukang = extra.getInt("id");
            namaTukang = extra.getString("name");
            photoTukang = extra.getString("photo");
            lat1 = extra.getDouble("lat1");
            long1 = extra.getDouble("long1");
        }

        CustomerApi customerApi = RetrofitApi.getCustomer();
        Call<CustomerResponse> customerCall = customerApi.getCustomer(uidCustomer);
        customerCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                loadingDialog.startLoading();
                textNamaUser.setText(response.body().getName());
                textNoHpUser.setText(" | +"+ response.body().getNumber());
                alamat = response.body().getAddres();
                inputAlamat.setText(alamat);
                lat2 = response.body().getLat();
                long2 = response.body().getLng();

                getJarakTukang(lat1,long1,lat2,long2);
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
            }
        });

        String urlImg = "http://omahdilit.my.id/public/images/" + photoTukang;
        Picasso.get().load(urlImg).fit().centerCrop().into(imageTukang);
        textNamaTukang.setText(namaTukang);
    }

    @OnClick(R.id.konfirmasipesanan_button_pilihModelRambut) void onPilihModelClick(){
        Intent intent = new Intent(KonfirmasiPesanan.this, PilihModelRambut.class);
        startActivityForResult(intent,1);
    }

    @OnClick(R.id.konfirmasipesanan_button_repickModelRambut) void onRepickClick(){
        Intent intent = new Intent(KonfirmasiPesanan.this, PilihModelRambut.class);
        startActivityForResult(intent,1);
    }

    @OnClick(R.id.konfirmasipesanan_button_konfirmasiPesanan) void onConfirmClick(){
        loadingDialog.startLoading();
        TransaksiApi api = RetrofitApi.createTransaksi();
        Call<TransaksiResponse> call = api.createTransaksi(idTukang, uidCustomer, idModelRambut, hargatotal, potongantotal, alamat,jarak, lat2, long2);
        call.enqueue(new Callback<TransaksiResponse>() {
            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                if (response.body()!=null){
                    boolean eror = response.body().getError();
                    if (!eror){
                        loadingDialog.dismissLoading();
                        Intent intent = new Intent(KonfirmasiPesanan.this, DetailPesanan.class);
                        intent.putExtra("kodeTransaksi", response.body().getKodeTrans());
                        startActivity(intent);
                        finish();
                    } else{
                        Toast.makeText(KonfirmasiPesanan.this, "transaksi gagal", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(KonfirmasiPesanan.this, "Error : "+t, Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.konfirmasipesanan_input_alamat)void onInputAlamatClick(){
        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).setCountry("ID")
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @OnClick(R.id.konfirmasipesanan_button_pilihPromo) void onPilihPromoClick(){
        Intent intent = new Intent(KonfirmasiPesanan.this, PilihPromo.class);
        intent.putExtra("idpromo", idPromo);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);

                alamat = place.getAddress();
                inputAlamat.setText(alamat);

                LatLng latLng = place.getLatLng();
                lat2 = latLng.latitude;
                long2 = latLng.longitude;

                getJarakTukang(lat1,long1,lat2,long2);

//                Toast.makeText(DetailPesanan.this, "Alamat :" + place.getAddress()+", Lat Long : "+place.getLatLng(), Toast.LENGTH_LONG).show();
//                Log.i("tag", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("tag", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                layoutPilihModel.setVisibility(View.GONE);
                idModelRambut = data.getIntExtra("id", 0);
                DetailModelApi api = RetrofitApi.getDetailModel();
                Call<DetailModelItem> call = api.getDetailModel(String.valueOf(idModelRambut));
                call.enqueue(new Callback<DetailModelItem>() {
                    @Override
                    public void onResponse(Call<DetailModelItem> call, Response<DetailModelItem> response) {
                        if (response.body()!=null){
                            layoutModelRambut.setVisibility(View.VISIBLE);
                            textNamaModel.setText(response.body().getNamaModel());
                            String urlImg = "http://omahdilit.my.id/public/images/" + response.body().getPhoto1();
                            Picasso.get().load(urlImg).fit().centerCrop().into(imageModelRambut);
                            textGayaRambut.setText(response.body().getKategori());
                            textDetailModelRambut.setText(response.body().getDetail());
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailModelItem> call, Throwable t) {

                    }
                });

            }
        }
        if (requestCode == 3){
            if (resultCode==RESULT_OK){
                loadingDialog.startLoading();
                DetailPromoApi api = RetrofitApi.getDetailPromo();
                idPromo = data.getIntExtra("id", 0);
                Call<DetailPromoItem> call = api.getDetailPromo(idPromo);
                call.enqueue(new Callback<DetailPromoItem>() {
                    @Override
                    public void onResponse(Call<DetailPromoItem> call, Response<DetailPromoItem> response) {
                        if (response.body() != null){
                            hitungHarga(jarak,idPromo);
                        }
                    }
                    @Override
                    public void onFailure(Call<DetailPromoItem> call, Throwable t) {

                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getJarakTukang(double lat1, double long1, double lat2, double long2){

        loadingDialog.checkLoading();
        JarakTukangApi api = RetrofitApi.getJarakTukang();
        Call<JarakResponse> call = api.getJarakTukang(lat1,long1,lat2,long2);
        call.enqueue(new Callback<JarakResponse>() {
            @Override
            public void onResponse(Call<JarakResponse> call, Response<JarakResponse> response) {
                if (response.body() != null){
                    jarak = response.body().getJarak();
                    String textJarak = jarak+" Km dari kamu";
                    hitungHarga(jarak, idPromo);


                    textJarakTukang.setText(textJarak);
                }
            }

            @Override
            public void onFailure(Call<JarakResponse> call, Throwable t) {

            }
        });
    }

    private void hitungHarga(Integer jaraktukang, Integer promo){
        HargaApi api = RetrofitApi.hitungHarga();
        Call<HargaResponse> call = api.hitungHarga(jaraktukang, promo);
        call.enqueue(new Callback<HargaResponse>() {
            @Override
            public void onResponse(Call<HargaResponse> call, Response<HargaResponse> response) {
                if (response.body()!=null) {
                    Locale localeID = new Locale("id", "ID");
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
                    textHargaCukur.setText(numberFormat.format(response.body().getHargacukur()));
                    textHargaPenanganan.setText(numberFormat.format(response.body().getHargalebih()));
                    hargatotal = response.body().getHargatotal();
                    potongantotal = response.body().getPotongan();
                    if (response.body().getPotongan()==0){
                        textTotalPotongan.setText(numberFormat.format(potongantotal));
                        textPotongan.setText(numberFormat.format(potongantotal));
                    } else if (response.body().getPotongan()!=0){
                        textPotongan.setText("-" + numberFormat.format(potongantotal));
                        textTotalPotongan.setText("-" + numberFormat.format(potongantotal));
                    }
                    textHarga.setText(numberFormat.format(hargatotal));
                    loadingDialog.dismissLoading();
                }
            }

            @Override
            public void onFailure(Call<HargaResponse> call, Throwable t) {

            }
        });
    }
}