package com.app.omahdilit.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.DetailPesanan;
import com.app.omahdilit.DetailPesanan_ViewBinding;
import com.app.omahdilit.MainActivity;
import com.app.omahdilit.ModelRambut;
import com.app.omahdilit.PilihTukangCukur;
import com.app.omahdilit.PilihTukangCukur_ViewBinding;
import com.app.omahdilit.Promo;
import com.app.omahdilit.R;
import com.app.omahdilit.Riwayat;
import com.app.omahdilit.adapter.AboutSliderAdapter;
import com.app.omahdilit.adapter.PromoBannerAdapter;
import com.app.omahdilit.api.CekTransaksiApi;
import com.app.omahdilit.api.PromoBannerApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.CekTransaksiResponse;
import com.app.omahdilit.response.PromoItem;
import com.app.omahdilit.response.PromoResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @BindView(R.id.card_home_promo) ViewPager card_home_promo;
    @BindView(R.id.card_home_about) RecyclerView card_home_about;
    @BindView(R.id.indicator_home_promo) WormDotsIndicator indicator_home_promo;
    @BindView(R.id.menu_home_model)
    LinearLayout menu_home_model;
    @BindView(R.id.home_text_nama)
    TextView home_text_nama;
    @BindView(R.id.home_layout_pesanan)
    CardView layoutPesanan;
    @BindView(R.id.home_text_status)
    TextView textStatus;
    @BindView(R.id.home_layout_swipe)
    SwipeRefreshLayout layoutRefresh;
    private HomeViewModel homeViewModel;
    Context context;
    List<PromoItem> mResponse;
    PromoBannerAdapter bannerAdapter;

    FirebaseUser user;

    String kodeTransaksi;

    Boolean pesananAktif;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        user = FirebaseAuth.getInstance().getCurrentUser();
        home_text_nama.setText(user.getDisplayName());

        mResponse = new ArrayList<>();
        bannerAdapter = new PromoBannerAdapter(getActivity(), mResponse);
        card_home_promo.setAdapter(bannerAdapter);
        card_home_promo.setPageMargin(48);
        card_home_promo.setClipToPadding(true);
        getData();

        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                layoutRefresh.setRefreshing(false);
            }
        });

        return root;
    }

    private void getData() {

        PromoBannerApi api = RetrofitApi.getApiPromoBaner();
        Call<PromoResponse> call = api.getPromoBanner();
        call.enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.body() != null){
                    mResponse = new ArrayList<>();
                    mResponse = response.body().getPromoItem();
                    bannerAdapter = new PromoBannerAdapter(getActivity(), mResponse);
                    bannerAdapter.notifyDataSetChanged();
                    card_home_promo.setAdapter(bannerAdapter);
                    card_home_promo.setPageMargin(48);
                    card_home_promo.setClipToPadding(true);
                    indicator_home_promo.setViewPager(card_home_promo);
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {

            }
        });
        card_home_about.setAdapter(new AboutSliderAdapter(this.getActivity()));
        card_home_about.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));

        CekTransaksiApi api1 = RetrofitApi.cekTransaksi();
        Call<CekTransaksiResponse> call1 = api1.cekTransaksi(user.getUid());
        call1.enqueue(new Callback<CekTransaksiResponse>() {
            @Override
            public void onResponse(Call<CekTransaksiResponse> call, Response<CekTransaksiResponse> response) {
                if (response.body().getStatus()!=null){
                    pesananAktif = true;
                    int status = response.body().getStatus();
                    kodeTransaksi = response.body().getKodeTransaksi();
                    layoutPesanan.setVisibility(View.VISIBLE);
                    if (status==0){
                        textStatus.setText("Menunggu konfirmasi");
                    } else if (status==1){
                        textStatus.setText("Tukang cukur sedang menuju ke tempatmu");
                    }
                } else{
                    layoutPesanan.setVisibility(View.GONE);
                    pesananAktif = false;
                }
            }

            @Override
            public void onFailure(Call<CekTransaksiResponse> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.home_layout_pesanan) void onPesananClick(){
        Intent intent = new Intent(getActivity(), DetailPesanan.class);
        intent.putExtra("kodeTransaksi", kodeTransaksi);
        startActivity(intent);
    }

    @OnClick(R.id.menu_home_cukur) void onCukurClick(){
        if (!pesananAktif){
            Intent intent = new Intent(getActivity(), PilihTukangCukur.class);
            startActivity(intent);
        } else {

        }
    }

    @OnClick(R.id.menu_home_model) void onMenuClick(){
        Intent intent = new Intent(getActivity(), ModelRambut.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_home_promo) void onPromoClick(){
        Intent intent = new Intent(getActivity(), Promo.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_home_riwayat) void onRiwayatClick(){
        Intent intent = new Intent(getActivity(), Riwayat.class);
        startActivity(intent);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}