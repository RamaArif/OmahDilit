package com.app.omahdilit;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class LoadingDialog {
    private Activity activity;
    private Dialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    void startLoading(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_loading);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.loading_bg);
        dialog.setCancelable(false);
        dialog.show();

    }

    void dismissLoading(){
        dialog.dismiss();
    }
}
