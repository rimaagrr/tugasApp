package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.R;

public class SplashActivity extends AppCompatActivity {

    private static final String URL = "https://ghaniyyascarf.com/api/product";
    private TextView textView,textView2;
    private Animation topAnim, botAnim;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        textView = findViewById(R.id.splashText);
        Typeface customfont=Typeface.createFromAsset(getAssets(),"font/Vilsuve - Regular.ttf");
        textView.setTypeface(customfont);
        textView.setTypeface(null, Typeface.BOLD);

        textView2 = findViewById(R.id.splashText2);
        Typeface customfont2=Typeface.createFromAsset(getAssets(),"font/Vilsuve - Regular.ttf");
        textView2.setTypeface(customfont2);
        textView2.setTypeface(null, Typeface.BOLD);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        imageView = findViewById(R.id.logo);
        imageView.setAnimation(topAnim);
        textView.setAnimation(botAnim);
        textView2.setAnimation(botAnim);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread() {
            @Override
            public void run(){
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    TinyDB tinyDB = new TinyDB(SplashActivity.this);
                    if (tinyDB.getString("token") != null){
                        if (!tinyDB.getString("token").isEmpty()){
                            startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
                            finish();
                        } else {
                            Intent homePage = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(homePage);
                            finish();
                        }
                    }
                    else {
                        Intent homePage = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(homePage);
                        finish();
                    }
                }
            }
        };
        thread.start();
    }
}

