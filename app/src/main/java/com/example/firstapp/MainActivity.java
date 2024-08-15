package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView txtBOOK_STORE;
    RelativeLayout relativeLayout;
    Animation txtAnimation,layoutAnimation;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fall_down);
        layoutAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_to_top);

        txtBOOK_STORE = findViewById(R.id.txtBOOK_STORE);
        relativeLayout = findViewById(R.id.relMain);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setAnimation(layoutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtBOOK_STORE.setVisibility(View.VISIBLE);

                        txtBOOK_STORE.setAnimation(txtAnimation);


                    }
                }, 900);
            }
        }, 500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        },3000);
    }


}
