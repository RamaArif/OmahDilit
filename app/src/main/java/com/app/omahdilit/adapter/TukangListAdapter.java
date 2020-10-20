package com.app.omahdilit.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.KonfirmasiPesanan;
import com.app.omahdilit.R;
import com.app.omahdilit.response.JarakResponse;
import com.app.omahdilit.response.TukangListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TukangListAdapter extends RecyclerView.Adapter<TukangListAdapter.ViewHolder> {
    Context context;
    List<TukangListItem> mResponse;
    List<JarakResponse> jarakResponses;

    public TukangListAdapter(Context context, List<TukangListItem> mResponse) {
        this.context = context;
        this.mResponse = mResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tukang_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TukangListItem item = mResponse.get(position);

        String urlImg = "http://omahdilit.my.id/public/images/" + item.getPhoto();
        Picasso.get().load(urlImg).transform(new CropCircleTransformation()).into(holder.imgTukang);
        holder.namaTukang.setText(item.getName());
        holder.jenkelTukang.setText(item.getJenkel());
        if (item.getStatus().equals("1")) {
            holder.statusTukang.setText("Online");
            holder.statusTukang.setBackgroundResource(R.drawable.bg_status_online);
        } else if (item.getStatus().equals("0")) {
            holder.statusTukang.setText("Offline");
            holder.statusTukang.setBackgroundResource(R.drawable.bg_status_offline);
        } else if (item.getStatus().equals("2")) {
            holder.statusTukang.setText("In-Order");
            holder.statusTukang.setBackgroundResource(R.drawable.bg_status_inorder);
        }

        holder.layoutTukang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KonfirmasiPesanan.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", item.getId());
                bundle.putString("photo", item.getPhoto());
                bundle.putString("name", item.getName());
                bundle.putDouble("lat1", item.getLat());
                bundle.putDouble("long1", item.getLng());
                intent.putExtras(bundle);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mResponse.size() == 0){
            return 0;
        }
        return mResponse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgTukang;
        public TextView namaTukang;
        public TextView jenkelTukang;
        public TextView statusTukang;
        public ConstraintLayout layoutTukang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTukang = itemView.findViewById(R.id.item_tukang_image);
            namaTukang = itemView.findViewById(R.id.item_tukang_nama);
            jenkelTukang = itemView.findViewById(R.id.item_tukang_jenkel);
            statusTukang = itemView.findViewById(R.id.item_tukang_status);
            layoutTukang = itemView.findViewById(R.id.item_tukang_layout);
        }
    }
}
