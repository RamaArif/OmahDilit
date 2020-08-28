package com.app.omahdilit.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.ModelRambut;
import com.app.omahdilit.Promo;
import com.app.omahdilit.R;
import com.app.omahdilit.adapter.AboutSliderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @BindView(R.id.card_home_promo) ViewPager card_home_promo;
    @BindView(R.id.card_home_about) RecyclerView card_home_about;
    @BindView(R.id.indicator_home_promo) WormDotsIndicator indicator_home_promo;
    @BindView(R.id.menu_home_model)
    LinearLayout menu_home_model;
    @BindView(R.id.home_text_nama)
    TextView home_text_nama;
    private HomeViewModel homeViewModel;
    Context context;

    FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        home_text_nama.setText(user.getDisplayName());

//        card_home_promo.setAdapter(new PromoSliderAdapter(this.getActivity()));
//        card_home_promo.setPageMargin(48);
//        card_home_promo.setClipToPadding(true);
//        indicator_home_promo.setViewPager(card_home_promo);
//        card_home_about.setAdapter(new AboutSliderAdapter(this.getActivity()));
//        card_home_about.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));




//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
                
//            }
//        });
        return root;
    }

    @OnClick(R.id.menu_home_model) void onMenuClick(){
        Intent intent = new Intent(getActivity(), ModelRambut.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_home_promo) void onPromoClick(){
        Intent intent = new Intent(getActivity(), Promo.class);
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