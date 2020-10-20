package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.omahdilit.api.DetailPromoApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.DetailPromoItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPromo extends AppCompatActivity {
    
    Integer idPromo;
    LoadingDialog loadingDialog;

    @BindView(R.id.detailpromo_text_namaPromo)
    TextView promo_detail_namaPromo;
    @BindView(R.id.detailpromo_text_potongan) TextView promo_detail_potongan;
    @BindView(R.id.detailPromo_image_gambar)
    ImageView detailPromo_image_gambar;
    @BindView(R.id.detailpromo_text_expired) TextView promo_detail_expired;
    @BindView(R.id.detailpromo_text_tglmulai) TextView promo_detail_mulai;
    @BindView(R.id.detailpromo_text_detail) TextView promo_detail_detail;
    @BindView(R.id.detail_promo_collapsing)
    CollapsingToolbarLayout detail_promo_collapse;
    @BindView(R.id.detail_promo_appbar) AppBarLayout detail_promo_appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.detail_promo_toolbar);
        setSupportActionBar(toolbar);
        detail_promo_collapse.setTitle("Detail Promo");
        detail_promo_collapse.setContentScrimColor(ContextCompat.getColor(this, R.color.putih));
        detail_promo_collapse.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.putih));
        detail_promo_collapse.setExpandedTitleColor(ContextCompat.getColor(this, R.color.transparent));
        detail_promo_collapse.setExpandedTitleTextAppearance(R.style.AppbarBold);
        detail_promo_collapse.setCollapsedTitleTextAppearance(R.style.AppbarBold);

        detail_promo_collapse.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.textdarkGrey));
        detail_promo_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((detail_promo_collapse.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(detail_promo_collapse))) {
                            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                } else {
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_putih);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        });


//        toolbar.setNavigationIcon(R.drawable.ic_back_putih);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idPromo = bundle.getInt("id");
        }

        DetailPromoApi api = RetrofitApi.getDetailPromo();
        Call<DetailPromoItem> call = api.getDetailPromo(idPromo);
        call.enqueue(new Callback<DetailPromoItem>() {
            @Override
            public void onResponse(Call<DetailPromoItem> call, Response<DetailPromoItem> response) {
                if (response.body() != null){

                    String urlImg = "http://omahdilit.my.id/public/images/" + response.body().getUrlPromo();
                    Picasso.get().load(urlImg).fit().centerCrop().into(detailPromo_image_gambar);

                    promo_detail_namaPromo.setText(response.body().getNamaPromo());

                    String inputDateAkhir = response.body().getTglAkhirPromo();
                    String inputDateAwal = response.body().getTglAwalPromo();
                    Locale localeID = new Locale("id","ID");
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = null,date1 = null;
                    try {
                        date = inputFormat.parse(inputDateAkhir);
                        date1 = inputFormat.parse(inputDateAwal);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG, localeID);
                    promo_detail_expired.setText(dateFormat.format(date));
                    String potongan = "Rp"+response.body().getPotongan();
                    promo_detail_potongan.setText(potongan);

                    promo_detail_mulai.setText(dateFormat.format(date1));
                    promo_detail_detail.setText(response.body().getDetailPromo());
                }
            }
            @Override
            public void onFailure(Call<DetailPromoItem> call, Throwable t) {

            }
        });

        
    }

    private void setWindowFlag(DetailPromo detailPromo, int flagTranslucentStatus, boolean b) {
        Window win = detailPromo.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (b) {
            winParams.flags |= flagTranslucentStatus;
        } else {
            winParams.flags &= ~flagTranslucentStatus;
        }
        win.setAttributes(winParams);
    }
}