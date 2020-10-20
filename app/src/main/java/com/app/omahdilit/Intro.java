package com.app.omahdilit;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroCustomLayoutFragment;

public class Intro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_1));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_2));
        addSlide(AppIntroCustomLayoutFragment.newInstance(R.layout.intro_3));

        setSkipText("LEWATI");
        setSkipTextTypeface(R.font.muli_bold);
        setColorSkipButton(getResources().getColor(R.color.colorAccent));
        setDoneText("LANJUT");
        setDoneTextTypeface(R.font.muli_bold);
        setColorDoneText(getResources().getColor(R.color.colorPrimary));
        setIndicatorColor(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorAccent));
        setSeparatorColor(getResources().getColor(R.color.putih));
        setNextArrowColor(getResources().getColor(R.color.colorPrimary));

    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);

        Intent intent = new Intent(Intro.this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        Intent intent = new Intent(Intro.this, Login.class);
        startActivity(intent);
        finish();
    }
}