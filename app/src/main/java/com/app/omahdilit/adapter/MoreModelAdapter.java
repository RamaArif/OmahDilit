package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.R;
import com.app.omahdilit.response.ModelItem;

import java.util.List;

public class MoreModelAdapter extends RecyclerView.Adapter<MoreModelAdapter.ViewHolder> {

    Context context;
    List<ModelItem> modelItems;

    public MoreModelAdapter(Context context, List<ModelItem> modelItems) {
        this.context = context;
        this.modelItems = modelItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_model_slidermodel,parent,false);
        return new MoreModelAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



}
