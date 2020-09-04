package com.app.omahdilit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.DetailPromo;
import com.app.omahdilit.R;
import com.app.omahdilit.response.PromoItem;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder>{

    private Context context;
    private List<PromoItem> mResponse;

    public PromoAdapter(Context context, List<PromoItem> mResponse) {
        this.context = context;
        this.mResponse = mResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_promo_promos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PromoItem item = mResponse.get(position);

        String urlImg = "http://omahdilit.my.id/public/images/" + item.getUrlPromo();
        Picasso.get().load(urlImg).fit().centerCrop().into(holder.imgPromo);
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
        String idPromo = item.getIdPromo();

        holder.layoutPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPromo.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", idPromo);
                intent.putExtras(bundle);
                context.startActivity(intent);
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

        public ConstraintLayout layoutPromo;
        public ImageView imgPromo;
        public TextView namaPromo;
        public TextView tanggalPromo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutPromo     = itemView.findViewById(R.id.promo_layout_item);
            imgPromo        = itemView.findViewById(R.id.promo_item_image);
            namaPromo       = itemView.findViewById(R.id.promo_item_textnama);
            tanggalPromo    = itemView.findViewById(R.id.promo_item_tanggal);
        }
    }
}
