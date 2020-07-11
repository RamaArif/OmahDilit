package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.R;
import com.squareup.picasso.Picasso;

public class PromoSliderAdapter extends PagerAdapter {
//    private ArrayList<PromoResponse> mResponse;
    private Integer [] mResponse = {R.drawable.bg_item_promo, R.drawable.bg_item_promo,
        R.drawable.bg_item_promo,  R.drawable.bg_item_promo, R.drawable.bg_item_promo,};
    private Context context;
    private LayoutInflater layoutInflater;

    public PromoSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_home_promo, null);
        CardView item_promo_image = view.findViewById(R.id.item_promo_image);
        item_promo_image.setBackgroundResource(mResponse[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return mResponse.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
