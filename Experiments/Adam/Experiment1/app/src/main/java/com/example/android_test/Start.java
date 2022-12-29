package com.example.android_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv.clearAnimation();
        tv2.clearAnimation();
        Animation a2 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a2.reset();
        a2.setStartOffset(1000);

        tv.startAnimation(a);
        tv2.startAnimation(a2);
    }
}