package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.R;
import com.squareup.picasso.Picasso;

public class ModelGridAdapter extends RecyclerView.Adapter<ModelGridAdapter.ViewHolder> {

    private Integer [] imgModel = {R.drawable.img_grid_model, R.drawable.img_grid_model,
            R.drawable.img_grid_model, R.drawable.img_grid_model, R.drawable.img_grid_model,
            R.drawable.img_grid_model, R.drawable.img_grid_model, R.drawable.img_grid_model,
            R.drawable.img_grid_model, R.drawable.img_grid_model, R.drawable.img_grid_model,
            R.drawable.img_grid_model};
    private String [] nameModel = {"Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly",
            "Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly", "Rockabilly", };
    private Context context;

    public ModelGridAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ModelGridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_model_grid, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelGridAdapter.ViewHolder holder, int position) {
        Picasso.get().load(imgModel[position]).resize(300, holder.card_model_image.getMeasuredHeight()).centerCrop().into(holder.card_model_image);
        holder.text_model_namegrid.setText(nameModel[position]);

        holder.card_model_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return imgModel.length;
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
