package com.app.omahdilit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.omahdilit.R;
import com.app.omahdilit.response.RiwayatItem;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    Context context;
    List<RiwayatItem> riwayatItems;

    public RiwayatAdapter(Context context, List<RiwayatItem> riwayatItems) {
        this.context = context;
        this.riwayatItems = riwayatItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_riwayat_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatItem item = riwayatItems.get(position);

        String inputDate = item.getCreatedAt();
        Locale localeID = new Locale("id","ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.LONG, localeID);
//        String urlImg = "http://omahdilit.my.id/public/images/" + item.;
//        Picasso.get().load(urlImg).fit().centerCrop().into(imgModel);
        holder.textHarga.setText(numberFormat.format(item.getTotalharga()));
        holder.textAlamat.setText(item.getAlamat());

    }

    @Override
    public int getItemCount() {
        if (riwayatItems!=null){
            return riwayatItems.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgModel;
        public TextView textNamaModel, textAlamat, textTanggal, textNamaTukang, textHarga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgModel        = itemView.findViewById(R.id.item_riwayat_img);
            textNamaModel   = itemView.findViewById(R.id.item_riwayat_namaModel);
            textAlamat      = itemView.findViewById(R.id.item_riwayat_alamat);
            textTanggal     = itemView.findViewById(R.id.item_riwayat_tanggal);
            textNamaTukang  = itemView.findViewById(R.id.item_riwayat_namaTukang);
            textHarga       = itemView.findViewById(R.id.item_riwayat_harga);
        }
    }
}
