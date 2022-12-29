package com.example.android_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
        Button b = (Button) findViewById(R.id.button);
        tv.clearAnimation();
        tv2.clearAnimation();
        b.clearAnimation();
        Animation a2 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a2.reset();
        a2.setStartOffset(1000);
        Animation a3 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a3.reset();
        a3.setStartOffset(2000);

        tv.startAnimation(a);
        tv2.startAnimation(a2);
        b.startAnimation(a3);
        b.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               // setContentView(R.layout.activity_page2);
               // Navigation.findNavController(R.layout.fragment_page_2);
               startActivity(new Intent(Start.this, Page2.class));
           }
        });
    }
}