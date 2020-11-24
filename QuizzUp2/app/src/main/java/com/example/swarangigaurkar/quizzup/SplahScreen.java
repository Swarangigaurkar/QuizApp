package com.example.swarangigaurkar.quizzup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplahScreen extends AppCompatActivity {
private static int time_out=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);

        ImageView imageView=findViewById(R.id.iv);


        Animation slowshow= AnimationUtils.loadAnimation(this,R.anim.slowsee);
        imageView.setAnimation(slowshow);


        Thread mythread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(SplahScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e)
                {


                }

            }
        };

        mythread.start();

    }
}
