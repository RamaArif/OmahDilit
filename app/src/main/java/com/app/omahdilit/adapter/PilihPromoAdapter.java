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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.DetailPromo;
import com.app.omahdilit.R;
import com.app.omahdilit.response.PromoItem;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PilihPromoAdapter extends RecyclerView.Adapter<PilihPromoAdapter.ViewHolder> {

    Context context;
    List<PromoItem> promoItems;
    AdapterCallback adapterCallback;

    public PilihPromoAdapter(Context context, List<PromoItem> promoItems, AdapterCallback adapterCallback) {
        this.context = context;
        this.promoItems = promoItems;
        this.adapterCallback = adapterCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_pilihpromo_listpromo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PromoItem item = promoItems.get(position);

        holder.namaPromo.setText(String.valueOf(item.getNamaPromo()));
        String inputDate = item.getTglAkhirPromo();
        Locale localeID = new Locale("id","ID");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG, localeID);
        holder.tanggalPromo.setText(dateFormat.format(date));
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        holder.potonganPromo.setText(numberFormat.format(item.getPotongan()));

        holder.layoutPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallback.onCallback(item.getIdPromo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return promoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layoutPromo;
        public TextView namaPromo;
        public TextView tanggalPromo;
        public TextView potonganPromo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutPromo     = itemView.findViewById(R.id.item_pilihpromo_layout);
            namaPromo       = itemView.findViewById(R.id.item_pilihpromo_namapromo);
            tanggalPromo    = itemView.findViewById(R.id.item_pilihpromo_expired);
            potonganPromo   = itemView.findViewById(R.id.item_pilihpromo_potongan);
        }
    }
}
