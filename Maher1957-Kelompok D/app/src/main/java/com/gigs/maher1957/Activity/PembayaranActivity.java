package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.R;

public class PembayaranActivity extends AppCompatActivity {

    private Button btnBack;
    public static final String PAYMENT_URL = "payment_url";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_pembayaran);

        btnBack = findViewById(R.id.btnBackOrder);

        WebView webView = findViewById(R.id.web_view);
//        webView.loadUrl(paymentUrl);
        Intent intent = getIntent();
        if (intent.getData() != null) {
            webView.loadUrl(intent.getDataString());
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(false);

        webView.setWebChromeClient(new WebChromeClient() {
            // Show loading progress in activity's title bar.
            @Override
            public void onProgressChanged(WebView view, int progress) {
                setProgress(progress * 100);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            // When start to load page, show url in activity's title bar
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                setTitle(url);
            }
        });

        init();
    }

    private void init() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(PembayaranActivity.this);

                alertDialog2.setTitle("Back");
                alertDialog2.setMessage("Cancel a payment?");
                alertDialog2.setIcon(R.drawable.ic_arrow_left_24);
                alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(PembayaranActivity.this, HomePageActivity.class);
                                startActivity(intent);
                                Animatoo.animateCard(PembayaranActivity.this);
                                finish();
                            }
                        });
                alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog2.show();
            }
        });
    }
}