package com.app.omahdilit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.R;
import com.app.omahdilit.response.ModelItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PilihModelAdapter extends RecyclerView.Adapter<PilihModelAdapter.ViewHolder> {

    Context context;
    List<ModelItem> modelItems;

    AdapterCallback adapterCallback;


    public PilihModelAdapter(Context context, List<ModelItem> modelItems, AdapterCallback adapterCallback) {
        this.context = context;
        this.modelItems = modelItems;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_model_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelItem modelItem = modelItems.get(position);
        String urlImg = "http://omahdilit.my.id/public/images/" + modelItem.getPhoto1();
        Picasso.get().load(urlImg).fit().centerCrop().into(holder.card_model_image);
        holder.text_model_namegrid.setText(modelItem.getNamaModel());
        holder.card_model_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallback.onCallback(modelItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView card_model_image;
        public TextView text_model_namegrid;
        public CardView card_model_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card_model_image = itemView.findViewById(R.id.item_model_gridimage);
            text_model_namegrid = itemView.findViewById(R.id.model_item_name);
            card_model_layout = itemView.findViewById(R.id.item_model_gridLayout);
        }
    }
}
