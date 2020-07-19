package com.app.omahdilit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.adapter.ModelGridAdapter;
import com.app.omahdilit.adapter.ModelSliderAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModelRambut extends AppCompatActivity {

    @BindView(R.id.card_model_headline)
    ViewPager card_model_headline;
    @BindView(R.id.rv_model_gridmodel)
    RecyclerView rv_model_gridmodel;
    @BindView(R.id.indicator_model_headline)
    WormDotsIndicator indicator_model_headline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_rambut);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbarModelRambut);
        setSupportActionBar(toolbar);

        card_model_headline.setAdapter(new ModelSliderAdapter(this));
        card_model_headline.setPageMargin(48);
        card_model_headline.setClipToPadding(true);
        indicator_model_headline.setViewPager(card_model_headline);

        rv_model_gridmodel.setAdapter(new ModelGridAdapter(this));



    }
}