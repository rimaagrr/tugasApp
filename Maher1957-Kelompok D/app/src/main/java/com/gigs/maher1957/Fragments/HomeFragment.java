package com.gigs.maher1957.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.DetailOrderActivity;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.KategoriDetailActivity;
import com.gigs.maher1957.Activity.SplashActivity;
import com.gigs.maher1957.Adapters.KategoriAdapter;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.CartBaru.Data;
import com.gigs.maher1957.Models.Favorite.DataPostFav;
import com.gigs.maher1957.Models.Favorite.ResponsePostFav;
import com.gigs.maher1957.Models.Kategori.DataKategori;
import com.gigs.maher1957.Models.Kategori.ResponseKategori;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.Models.Pengiriman.ArrayResult;
import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.Models.Product.ResponseDataItem;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.Models.Slide.DataSlide;
import com.gigs.maher1957.Models.Slide.ResponseSlide;
import com.gigs.maher1957.Activity.DetailActivity;
import com.gigs.maher1957.Adapters.SlideAdapter;
import com.gigs.maher1957.Adapters.ItemAdapter;
import com.gigs.maher1957.R;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnItemClickListener, OnKategoriClickListener{
    public HomeFragment() {

    }
    private String token;
    private TinyDB tinyDB;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private DataPostFav dataPostFav;

    private TextView textView, textView2,judulKategori, thereborn, thereborn2, searchResult;
    private EditText search;

    /////////// Banner 1 ////////////
    private ViewPager2 viewPager2Slide1;
    private SlideAdapter slide1Adapter;
    private List<DataSlide> slide1Models;
    private Handler slide1Handler = new Handler();

    ///// Kategori //////////
    private RecyclerView recyclerViewKategori;
    private KategoriAdapter kategoriAdapter;
    private List<DataKategori> dataKategoriModel;

    /////// rekomendasi ///////
    private RecyclerView itemRecycleView;
    private ItemAdapter itemAdapter;
    private List<DataItem> itemModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    private void init() {
        tinyDB = new TinyDB(getContext());
        token = tinyDB.getString("token");
        search = (EditText) view.findViewById(R.id.cari);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
                recyclerViewKategori.setVisibility(View.GONE);
                viewPager2Slide1.setVisibility(View.GONE);
                judulKategori.setVisibility(View.GONE);
                searchResult.setVisibility(View.VISIBLE);
                searchResult.setText("Search Result :");
                searchResult.setTextSize(20);
            }
        });

        searchResult = (TextView) view.findViewById(R.id.textSearch);
        thereborn = (TextView) view.findViewById(R.id.thereborn);
        thereborn2 = (TextView) view.findViewById(R.id.thereborn2);
        judulKategori = (TextView) view.findViewById(R.id.judulKategori);
        textView = view.findViewById(R.id.homeText);
        Typeface customfont=Typeface.createFromAsset(getActivity().getAssets(),"font/Vilsuve - Regular.ttf");
        textView.setTypeface(customfont);
        textView.setTypeface(null, Typeface.BOLD);
        textView2 = view.findViewById(R.id.homeText2);
        Typeface customfont2=Typeface.createFromAsset(getActivity().getAssets(),"font/Vilsuve - Regular.ttf");
        textView2.setTypeface(customfont2);
        textView2.setTypeface(null, Typeface.BOLD);

        //////// Baner 1 model list /////
        viewPager2Slide1 = (ViewPager2) view.findViewById(R.id.viewPagerBanner1);
        ButterKnife.bind(viewPager2Slide1);

        RestClient.getService().getSlide().enqueue(new Callback<ResponseSlide>() {
            @Override
            public void onResponse(Call<ResponseSlide> call, Response<ResponseSlide> response) {
                if (response.isSuccessful()){
                    slide1Models = response.body().getData();
                    slide1Adapter = new SlideAdapter(slide1Models, viewPager2Slide1,getContext());
                    viewPager2Slide1.setAdapter(slide1Adapter);
                    viewPager2Slide1.setClipToPadding(false);
                    viewPager2Slide1.setClipChildren(false);
                    viewPager2Slide1.setOffscreenPageLimit(3);
                    viewPager2Slide1.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                    CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
                    compositePageTransformer.addTransformer(new MarginPageTransformer(3));
                    compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                        @Override
                        public void transformPage(@NonNull View page, float position) {
                            float r = 1 - Math.abs(position);
                            page.setScaleY(0.85f + r * 0.85f);
                        }
                    });
                    viewPager2Slide1.setPageTransformer(compositePageTransformer);
                    viewPager2Slide1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            slide1Handler.removeCallbacks(slide1Runnable);
                            slide1Handler.postDelayed(slide1Runnable, 5000); /// slide duration 3 seconds
                        }
                    });
                }
//                else{
//                    Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getContext());
//                    progressDialog.dismiss();
//                    alertDialog2.setTitle("Restart");
//                    alertDialog2.setMessage("Please Check your connection, and Restart");
//                    alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent showDetail = new Intent(getActivity(), SplashActivity.class);
//                            Animatoo.animateFade(Objects.requireNonNull(getContext()));
//                            startActivity(showDetail);
//                        }
//                    });
//                    alertDialog2.show();
//                }
            }

            @Override
            public void onFailure(Call<ResponseSlide> call, Throwable t) {

            }
        });

        //// kategori///
        recyclerViewKategori = (RecyclerView) view.findViewById(R.id.recyclerViewKategori);
        ButterKnife.bind(recyclerViewKategori);

        RestClient.getService().getKategori().enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if(response.isSuccessful() && response.body()!=null){
                    dataKategoriModel = response.body().getData();
                    judulKategori.setText("Category");
                    UpdateKategori(response.body().getData());

                }
//                else {
//                    Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getContext());
//                    progressDialog.dismiss();
//                    alertDialog2.setTitle("Restart");
//                    alertDialog2.setMessage("Please Check your connection, and Restart");
//                    alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent showDetail = new Intent(getActivity(), SplashActivity.class);
//                            Animatoo.animateFade(Objects.requireNonNull(getContext()));
//                            startActivity(showDetail);
//                        }
//                    });
//                    alertDialog2.show();
//                }
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                Log.d("asd","fail");
                Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();

            }
        });

        itemRecycleView = (RecyclerView) view.findViewById(R.id.recyclerViewRekomendasi);
        ButterKnife.bind(itemRecycleView);


        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        RestClient.getService().getDataItem().enqueue(new Callback<ResponseDataItem>() {
            @Override
            public void onResponse(@NonNull Call<ResponseDataItem> call, @NonNull Response<ResponseDataItem> response) {
                if (response.isSuccessful() && response.body() != null){
                    progressDialog.dismiss();
                    itemModel = response.body().getData();
                    searchResult.setVisibility(View.GONE);
                    thereborn.setText(R.string.rekomendasi);
                    thereborn.setTextSize(22);
                    thereborn2.setText(R.string.rekomendasi2);
                    thereborn2.setTextSize(14);
                    UpdateItemGrid(response.body().getData());
                }
                else{
                    Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getContext());
                    progressDialog.dismiss();
                    alertDialog2.setTitle("Restart");
                    alertDialog2.setMessage("Please Check your connection, and Restart");
                    alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent showDetail = new Intent(getActivity(), SplashActivity.class);
                            Animatoo.animateFade(Objects.requireNonNull(getContext()));
                            startActivity(showDetail);
                        }
                    });
                    alertDialog2.show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseDataItem> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
                Intent showDetail = new Intent(getContext(), HomePageActivity.class);
                startActivity(showDetail);
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }

    private void closeKeyboard() {
        View view = Objects.requireNonNull(this.getActivity()).getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @OnTextChanged(R.id.cari)
    protected void onTextChanged(CharSequence text) {
        filter(text.toString());
    }

    private void filter(String text) {
        List<DataItem> filteredList = new ArrayList<>();
        for (DataItem item : itemModel) {
            if (item.getName().toLowerCase().contains(text)) {
                filteredList.add(item);
//            } else if (item.getDesc().toLowerCase().contains(text)){
//                    filteredList.add(item);
            }
            else if (item.getName().toLowerCase().contains(text)) {
                filteredList.add(item);
//            } else if (item.getDesc().toLowerCase().contains(text)){
//                    filteredList.add(item);
            }
        }
        itemAdapter.filterList(filteredList);
    }

    private void UpdateKategori(List<DataKategori> data) {
        kategoriAdapter = new KategoriAdapter(data,getContext(),this);
        LinearLayoutManager layoutManagerKategori = new LinearLayoutManager(getContext());
        layoutManagerKategori.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewKategori.setLayoutManager(layoutManagerKategori);
        recyclerViewKategori.setHasFixedSize(true);
        recyclerViewKategori.setAdapter(kategoriAdapter);
    }

    private void UpdateItemGrid(List<DataItem> data) {
        itemAdapter = new ItemAdapter(data,getContext(),this);
        itemRecycleView.setLayoutManager(new GridLayoutManager(getContext(),2));
        itemRecycleView.setAdapter(itemAdapter);
    }

    @Override
    public void onItemClick(int itemId) {
//        if (token.equals("")){
//            Intent showDetail = new Intent(getContext(), EmailLoginActivity.class);
//            startActivity(showDetail);
//            Animatoo.animateCard(Objects.requireNonNull(getContext()));
//            Toast.makeText(getContext(), "Silahkan login", Toast.LENGTH_SHORT).show();
//        } else{
            Intent showDetail = new Intent(getContext(), DetailActivity.class);
            showDetail.putExtra(DetailActivity.ITEM_ID,itemId);
            showDetail.putExtra(DetailActivity.PRODUCT_ID,itemId);
            startActivity(showDetail);
//        }
    }

    @Override
    public void onKategoriClick(String slug) {
        Intent showDetail = new Intent(getContext(), KategoriDetailActivity.class);
        showDetail.putExtra("slug", slug);
        startActivity(showDetail);
    }
//    @Override
//    public void onFavClick(int itemFav) {
//        int userID = tinyDB.getObject("user_id", Order.class).getId();
//
//        if (token.equals("")) {
//            Intent showDetail = new Intent(getContext(), EmailLoginActivity.class);
//            startActivity(showDetail);
//            Animatoo.animateCard(Objects.requireNonNull(getContext()));
//            Toast.makeText(getContext(), "Silahkan login", Toast.LENGTH_SHORT).show();
//        } else {
//            RestClient.getService().postFav("Bearer " + token, itemFav, userID).enqueue(new Callback<ResponsePostFav>() {
//                @Override
//                public void onResponse(Call<ResponsePostFav> call, Response<ResponsePostFav> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        dataPostFav = response.body().getDataPostFav();
//                        Toast.makeText(getContext(), " Ditambahkan ke wishlist", Toast.LENGTH_SHORT).show();
//                        Fragment someFragment = new FavoriteFragment();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.replace(R.id.layout_home, someFragment ); // give your fragment container id in first parameter
//                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                        transaction.commit();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsePostFav> call, Throwable t) {
//                    Log.d("asd", "fail");
//                }
//            });
//        }
//    }

    private Runnable slide1Runnable = new Runnable() {
        @Override
        public void run() {
            viewPager2Slide1.setCurrentItem(viewPager2Slide1.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slide1Handler.removeCallbacks(slide1Runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slide1Handler.postDelayed(slide1Runnable,3000);
    }
}
