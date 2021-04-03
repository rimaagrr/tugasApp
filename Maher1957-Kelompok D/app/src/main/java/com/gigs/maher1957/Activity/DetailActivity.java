package com.gigs.maher1957.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Adapters.DetailAdapter;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.CartBaru.ResponsePostCartBaru;
import com.gigs.maher1957.Models.CartBaru.ResponseTokenCart;
import com.gigs.maher1957.Models.LoginRegister.Login.User;
import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.Models.Product.ProductColorsItem;
import com.gigs.maher1957.Models.Product.ProductImagesItem;
import com.gigs.maher1957.Models.Product.ProductSizesItem;
import com.gigs.maher1957.Models.Product.ResponseDataItem;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;
import com.thebluealliance.spectrum.SpectrumPalette;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TinyDB tinyDB;
    private DataItem dataItem;
    private TextView stock, name, desc, priceUtama, price, price2, disc, quantityTextView, weight, priceTotal;
    private Button btnAddToCart;
    private ImageView btnBack,image;
    private RadioGroup radioGroup;
    private SpectrumPalette spectrumPalette;
    private int color;
    private int colors[];
    private String colorName = "";
    private int mInteger = 1;
    public static final String ITEM_ID = "item_id";
    public static final String PRODUCT_ID = "product_id";
    private List<ProductSizesItem> ukuranModels;
    private List<ProductColorsItem> colorsItems;
    private HashMap<String,ProductSizesItem> hashMap;
    private ProductSizesItem selectedSize;
    private List<ProductImagesItem> productImagesItems;
    private DetailAdapter detailAdapter;
    private ViewPager2 viewPager2;
    private TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    private void init() {

        hashMap = new HashMap<>();
        tinyDB = new TinyDB(this);

        colorsItems = new ArrayList<>();
        ukuranModels = new ArrayList<>();
        productImagesItems = new ArrayList<>();

        indicator=(TabLayout) findViewById(R.id.indicator);
        /// here, i'm preparing list of images from drawable,
        /// you can get it from API as well
//
        btnAddToCart = findViewById(R.id.btn_addToCartGridFav);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = tinyDB.getString("token");
                int userID = tinyDB.getObject("user_id", User.class).getId();
                int productID = getIntent().getIntExtra(PRODUCT_ID,1);

                for (int i = 0; i < colors.length; i++){
                    if (color == colors[i]) {
                        colorName = colorsItems.get(i).getName();
                    }
                }
                if (token.equals("")){
                    Intent showDetail = new Intent(DetailActivity.this, EmailLoginActivity.class);
                    startActivity(showDetail);
                    Animatoo.animateCard(Objects.requireNonNull(DetailActivity.this));
                    Toast.makeText(DetailActivity.this, "Silahkan login", Toast.LENGTH_SHORT).show();
                    } else{
                        if (color == 0){
                            Toast.makeText(DetailActivity.this, "Please Choose a color and size", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (selectedSize == null){
                            Toast.makeText(DetailActivity.this, "Please Choose a color and size", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (tinyDB.getObject("cartToken", ResponseTokenCart.class)==null){
                            RestClient.getService().getTokenCart("Bearer " + token).enqueue(new Callback<ResponseTokenCart>() {
                                @Override
                                public void onResponse(Call<ResponseTokenCart> call, Response<ResponseTokenCart> response) {
                                    if(response.isSuccessful() && response.body() != null){
                                        tinyDB.putObject("cartToken",response.body());
                                    } else {
                                        Log.d("asd","response");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseTokenCart> call, Throwable t) {
                                    Log.d("asd","fail");
                                }
                            });
                            } else {
                                ResponseTokenCart responseTokenCart = tinyDB.getObject("cartToken",ResponseTokenCart.class);
                                RestClient.getService().postCartBaru("Bearer " + token, responseTokenCart.getCartToken(), responseTokenCart.getCartKey(),String.valueOf(userID),String.valueOf(productID),String.valueOf(mInteger),colorName,String.valueOf(selectedSize.getName()),name.getText().toString(),String.valueOf(dataItem.getWeight()),String.valueOf(dataItem.getPriceResult()),String.valueOf(dataItem.getImgHover())).enqueue(new Callback<ResponsePostCartBaru>() {
                                @Override
                                public void onResponse(Call<ResponsePostCartBaru> call, Response<ResponsePostCartBaru> response) {
                                if (response.isSuccessful() && response.body() != null){
                                    Toast.makeText(DetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                    Intent showDetail = new Intent(getBaseContext(), HomePageActivity.class);
                                    startActivity(showDetail);
                                } else{
                                    Toast.makeText(DetailActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                                }

                                @Override
                                public void onFailure(Call<ResponsePostCartBaru> call, Throwable t) {
                                    Log.d("asd","fail2");
                                    }
                                });
                            }
                        }

//                RestClient.getService().postCartV2("Bearer " +token, dataItem.getSlug().toString(), dataItem.getId(), productID, String.valueOf(mInteger), colorName, String.valueOf(selectedSize.getName())).enqueue(new Callback<ResponsePostCartv2>() {
//                    @Override
//                    public void onResponse(Call<ResponsePostCartv2> call, Response<ResponsePostCartv2> response) {
//                        if (response.isSuccessful() && response.body() != null){
//                            Toast.makeText(DetailActivity.this, "Ditambahkan kedalam cart", Toast.LENGTH_SHORT).show();
//                        }
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsePostCartv2> call, Throwable t) {
//                        Log.d("asd","fail");
//
//                    }
//                });

//                RestClient.getService().postCarts("Bearer " + token, cartKey.getCartToken(), cartKey.getCartKey(), String.valueOf(productID), name.getText().toString(), String.valueOf(mInteger), colorName, String.valueOf(selectedSize.getName()), String.valueOf(userID), String.valueOf(dataItem.getPriceResult()), String.valueOf(dataItem.getWeight()), String.valueOf(dataItem.getImgHover())).enqueue(new Callback<ResponsePostCart>() {
//                    @Override
//                    public void onResponse(Call<ResponsePostCart> call, Response<ResponsePostCart> response) {
//                        if (response.isSuccessful() && response.body() != null){
//                            Toast.makeText(DetailActivity.this, "Ditabahkan kedalam cart", Toast.LENGTH_SHORT).show();
//                        }
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponsePostCart> call, Throwable t) {
//                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        ////////// PILIH UKURAN ///////
        radioGroup = findViewById(R.id.btnRadio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedSize = hashMap.get(String.valueOf(checkedId));
            }
        });

        //////// PILIH WARNA ///////////
        spectrumPalette = findViewById(R.id.color);
        spectrumPalette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int clr) {
                color = clr;
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(DetailActivity.this);
                finish();
            }
        });

        viewPager2 = (ViewPager2) findViewById(R.id.viewPager);
        stock = findViewById(R.id.stock);
        name = findViewById(R.id.nama);
        desc = findViewById(R.id.deskripsi);
        priceUtama = findViewById(R.id.priceUtama);
        price = findViewById(R.id.price);
        price2 = findViewById(R.id.price2);
//        disc = findViewById(R.id.disc);
//        weight = findViewById(R.id.weight);
        quantityTextView = findViewById(R.id.text_quantity);
        image = findViewById(R.id.imageDetail);

        int itemID = getIntent().getIntExtra(ITEM_ID,1);
        getItemDetails(itemID);
    }


    private void getItemDetails(int itemID) {

        final ProgressDialog progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        RestClient.getService().getDetailItem(itemID).enqueue(new Callback<ResponseDataItem>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDataItem> call, @NonNull Response<ResponseDataItem> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    progressDialog.dismiss();

                    DataItem dataItemTemp = response.body().getData().get(0);
                    dataItem = response.body().getData().get(0);

                    productImagesItems.addAll(dataItemTemp.getProductImages());

                    detailAdapter = new DetailAdapter(getApplicationContext(),productImagesItems);
                    viewPager2.setClipToPadding(true);
                    viewPager2.setClipChildren(false);
                    viewPager2.setOffscreenPageLimit(3);
                    viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
                    viewPager2.setAdapter(detailAdapter);

                    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                    compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                        @Override
                        public void transformPage(@NonNull View page, float position) {
                            float r = 1 - Math.abs(position);
                            page.setScaleY(0.85f + r * 0.15f);
                        }
                    });
                    viewPager2.setPageTransformer(compositePageTransformer);
                    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                        }
                    });
                    new TabLayoutMediator(indicator, viewPager2,
                            new TabLayoutMediator.TabConfigurationStrategy() {
                                @Override
                                public void onConfigureTab(@NonNull TabLayout.Tab tab, int o) {

                                }
                            }).attach();

                    colors = new int[dataItemTemp.getProductColors().size()];
                    int i = 0;
                    for (ProductColorsItem productColorsItem : dataItemTemp.getProductColors()) {
                        colors[i] = (Color.parseColor(productColorsItem.getCode()));
                        i = i + 1;
                    }

                    ukuranModels.clear();
                    ukuranModels.addAll(dataItem.getProductSizes());
                    colorsItems.clear();
                    colorsItems.addAll(dataItem.getProductColors());
                    productImagesItems.clear();
                    productImagesItems.addAll(dataItem.getProductImages());
                    spectrumPalette.setColors(colors);
                    spectrumPalette.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    updateUI(dataItemTemp);

                    radioGroup.setOrientation(LinearLayout.VERTICAL);
                    for (int j = 0;j < ukuranModels.size(); j++) {
                        int radioID = View.generateViewId();
                        RadioButton rdbtn = new RadioButton(DetailActivity.this);
                        rdbtn.setId(radioID);
                        rdbtn.setText(ukuranModels.get(j).getName());
                        radioGroup.addView(rdbtn);
                        hashMap.put(String.valueOf(radioID),ukuranModels.get(j));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseDataItem> call, @NonNull Throwable t) {
                Log.d("asd","eror");
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(DataItem dataItem) {

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        String harga = dataItem.getPrice();
        String harga2 = dataItem.getPriceResult();
        String diskon = dataItem.getDisc();

//        weight.setText(dataItem.getWeight()+"gr");
        stock.setText(dataItem.getStock());
        name.setText(dataItem.getName());
//        desc.setText(dataItem.getDesc());
        priceUtama.setText(formatRupiah.format((double)Integer.parseInt(harga)));
        price.setText(formatRupiah.format((double)Integer.parseInt(harga)));
        price2.setText(formatRupiah.format((double)Integer.parseInt(harga2)));
        price2.setTypeface(null, Typeface.BOLD);
//        disc.setText(diskon+"%");

        if (diskon != null){
            priceUtama.setVisibility(View.GONE);
            price.setPaintFlags(price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            price.setTextSize(19);

        } else {
            priceUtama.setTypeface(null, Typeface.BOLD);
            priceUtama.setTextColor(Color.parseColor("#000000"));
            price2.setVisibility(View.GONE);
            price.setVisibility(View.GONE);
//            disc.setVisibility(View.GONE);
        }
    }

    public void decreaseInteger(View view) {
        if (mInteger == 1) {
            Toast.makeText(this, "Order min 1", Toast.LENGTH_SHORT).show();
            return;
        }
        mInteger = mInteger - 1;
        display(mInteger);
    }

    public void increaseInteger(View view) {
        int maxStock = Integer.parseInt(dataItem.getStock());
        if (mInteger == maxStock ) {
            Toast.makeText(this, "Order max", Toast.LENGTH_SHORT).show();
            return;
        }
        mInteger = mInteger + 1;
        display(mInteger);
    }

    private void display(int number) {
        quantityTextView = findViewById(R.id.text_quantity);
        quantityTextView.setText("" + number);
    }
}