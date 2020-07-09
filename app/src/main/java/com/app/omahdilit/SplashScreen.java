package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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

                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //  Initialize SharedPreferences
                                        SharedPreferences getPrefs = PreferenceManager
                                                .getDefaultSharedPreferences(getBaseContext());

                                        //  Create a new boolean and preference and set it to true
                                        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                                        //  If the activity has never started before...
                                        if (isFirstStart) {

                                            //  Launch app intro
                                            final Intent intent = new Intent(SplashScreen.this, Intro.class);
                                            startActivity(intent);
                                            finish();

                                            runOnUiThread(new Runnable() {
                                                @Override public void run() {
                                                    startActivity(intent);
                                                }
                                            });

                                            //  Make a new preferences editor
                                            SharedPreferences.Editor e = getPrefs.edit();

                                            //  Edit preference to make it false because we don't want this to run again
                                            e.putBoolean("firstStart", false);

                                            //  Apply changes
                                            e.apply();
                                        }
                                        else {
                                            final Intent intent = new Intent(SplashScreen.this, Login.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });

                                t.start();
                            }
                        }, 500);
                    }
                }, 3000);
            }
        }, 1000);
    }
}