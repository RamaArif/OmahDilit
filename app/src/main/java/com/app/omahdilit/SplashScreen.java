package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    @BindView(R.id.container) ViewGroup container;
    @BindView(R.id.txt_splash1) TextView txtSplash1;
    @BindView(R.id.txt_splash2) TextView txtSplash2;
    @BindView(R.id.txt_splash3) TextView txtSplash3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        ButterKnife.bind(this);

        Fade fade = new Fade();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(container, fade);
                txtSplash1.setVisibility(View.VISIBLE);
                txtSplash2.setVisibility(View.VISIBLE);
                txtSplash3.setVisibility(View.VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TransitionManager.beginDelayedTransition(container, fade);
                        txtSplash1.setVisibility(View.GONE);
                        txtSplash2.setVisibility(View.GONE);
                        txtSplash3.setVisibility(View.GONE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SplashScreen.this, Intro.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 500);
                    }
                }, 3000);
            }
        }, 1000);
    }
}