package com.app.omahdilit;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ViewDialog {
    public void showDialog(Activity activity, Integer striing_msg){
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_alert);
        dialog.setContentView(R.layout.alert_base);

        TextView textAlert = dialog.findViewById(R.id.text_alert_pesan);
        TextView btnAlert = dialog.findViewById(R.id.button_alert_oke);

        textAlert.setText(striing_msg);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
