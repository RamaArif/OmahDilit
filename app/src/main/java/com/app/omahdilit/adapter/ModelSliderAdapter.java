package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ModelSliderAdapter extends PagerAdapter {

    private Integer [] imgList  =  {R.drawable.img_slider_model, R.drawable.img_slider_model, R.drawable.img_slider_model,
            R.drawable.img_slider_model, R.drawable.img_slider_model, };
    private String [] nameList =  {"Pompador","Pompador","Pompador","Pompador","Pompador"};
    private Context context;
    private LayoutInflater layoutInflater;

    public ModelSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_model_slidermodel, null);
        ImageView item_model_headline = view.findViewById(R.id.item_model_headline);
        TextView item_model_headlinetext = view.findViewById(R.id.text_model_namaslider);
        Picasso.get().load(imgList[position]).resize(item_model_headline.getMeasuredWidth(), 500).centerCrop().into(item_model_headline);
        item_model_headlinetext.setText(nameList[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return imgList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
