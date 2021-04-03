package com.gigs.maher1957.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.R;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private LinearLayout emailContinue;
    private Button btnSkip;
    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textView = findViewById(R.id.textMain);
        Typeface customfont=Typeface.createFromAsset(getAssets(),"font/Vilsuve - Regular.ttf");
        textView.setTypeface(customfont);
        textView.setTypeface(null, Typeface.BOLD);
        textView2 = findViewById(R.id.textMain2);
        Typeface customfont2=Typeface.createFromAsset(getAssets(),"font/Vilsuve - Regular.ttf");
        textView2.setTypeface(customfont2);
        textView2.setTypeface(null, Typeface.BOLD);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        btnSkip = (Button) findViewById(R.id.button);
        emailContinue = (LinearLayout) findViewById(R.id.linearEmailContinue);
        emailContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, EmailLoginActivity.class);
                startActivity(intent);
                finish();
                Animatoo.animateSlideDown(MainActivity.this);
            }
        });
    }

    public void goToHomePage(View view) {
        Intent intent = new Intent (MainActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
        Animatoo.animateSwipeLeft(this);
    }
}
