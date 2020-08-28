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
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.DetailModel;
import com.app.omahdilit.R;
import com.app.omahdilit.response.ModelItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ModelRambutAdapter extends RecyclerView.Adapter<ModelRambutAdapter.ViewHolder> {

    private List<ModelItem> modelItems;
    private Context context;

    public ModelRambutAdapter( Context context, List<ModelItem> modelItems) {
        this.modelItems = modelItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ModelRambutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_model_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelRambutAdapter.ViewHolder holder, int position) {
        final ModelItem modelItem = modelItems.get(position);
        String urlImg = "http://omahdilit.my.id/public/images/" + modelItem.getPhoto1();
        Picasso.get().load(urlImg).fit().centerCrop().into(holder.card_model_image);
        holder.text_model_namegrid.setText(modelItem.getNamaModel());
        holder.card_model_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailModel.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(modelItem.getId()));
                intent.putExtras(bundle);
                context.startActivity(intent);
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
            text_model_namegrid = itemView.findViewById(R.id.text_model_gridname);
            card_model_layout = itemView.findViewById(R.id.item_model_gridLayout);
        }
    }
}
