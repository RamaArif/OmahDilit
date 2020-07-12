package com.app.omahdilit.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.R;
import com.app.omahdilit.adapter.AboutSliderAdapter;
import com.app.omahdilit.adapter.PromoSliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.card_home_promo) ViewPager card_home_promo;
    @BindView(R.id.card_home_about) RecyclerView card_home_about;
    @BindView(R.id.indicator_home_promo) WormDotsIndicator indicator_home_promo;
    private HomeViewModel homeViewModel;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);

        card_home_promo.setAdapter(new PromoSliderAdapter(this.getActivity()));
        card_home_promo.setPageMargin(48);
        card_home_promo.setClipToPadding(true);
        indicator_home_promo.setViewPager(card_home_promo);

        card_home_about.setAdapter(new AboutSliderAdapter(this.getActivity()));
        card_home_about.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL,false));




//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
                
//            }
//        });
        return root;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}