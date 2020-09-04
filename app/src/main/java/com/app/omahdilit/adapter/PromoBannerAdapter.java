package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.R;
import com.app.omahdilit.response.PromoItem;
import com.app.omahdilit.response.PromoResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PromoBannerAdapter extends PagerAdapter {
    Context context;
    List<PromoItem> promoItems;
    LayoutInflater layoutInflater;

    public PromoBannerAdapter(Context context, List<PromoItem> promoItems) {
        this.context = context;
        this.promoItems = promoItems;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (promoItems.size() == 0){
            return 0;
        }
        return promoItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_home_promo, container,false);

        final PromoItem item = promoItems.get(position);

        ImageView home_image_promoSlider = view.findViewById(R.id.home_image_promoslider);

        String urlImg = "http://omahdilit.my.id/public/images/" + item.getUrlPromo();
        Picasso.get().load(urlImg).fit().centerCrop().into(home_image_promoSlider);

        ViewPager vp = (ViewPager) container;

        vp.addView(view, 0);

        return view;
    }

    @Override    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;

        View view = (View) object;

        vp.removeView(view);;
    }

}
