package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.R;

public class AboutSliderAdapter extends RecyclerView.Adapter<AboutSliderAdapter.ViewHolder> {

    private Integer [] mResponse = {R.drawable.bg_item_about, R.drawable.bg_item_about,
            R.drawable.bg_item_about,R.drawable.bg_item_about,R.drawable.bg_item_about,R.drawable.bg_item_about,};
    private Context context;

    public AboutSliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AboutSliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home_about,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutSliderAdapter.ViewHolder holder, int position) {
        holder.item_home_about.setBackgroundResource(mResponse[position]);

    }

    @Override
    public int getItemCount() {
        return mResponse.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView item_home_about;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_home_about = itemView.findViewById(R.id.item_home_about);
        }
    }
}
