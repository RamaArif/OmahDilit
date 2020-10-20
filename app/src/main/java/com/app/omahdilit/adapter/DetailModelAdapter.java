package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.omahdilit.R;
import com.squareup.picasso.Picasso;

public class DetailModelAdapter extends PagerAdapter {
    Context context;
    String[] uriFoto;

    public DetailModelAdapter(Context context,String[] uriFoto) {
        this.context = context;
        this.uriFoto = uriFoto;
    }

    @Override
    public int getCount() {
        if (uriFoto.length == 0){
            return 0;
        }
        return uriFoto.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detailmodel_slider, container,false);

        ImageView item_detailmodel_image = view.findViewById(R.id.item_detailmodel_image);

        String urlImg = "http://omahdilit.my.id/public/images/" + uriFoto[position];
        Picasso.get().load(urlImg).fit().centerCrop().into(item_detailmodel_image);

        ViewPager vp = (ViewPager) container;

        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;

        View view = (View) object;

        vp.removeView(view);;
    }
}
