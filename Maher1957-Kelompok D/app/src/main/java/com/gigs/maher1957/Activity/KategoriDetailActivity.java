package com.gigs.maher1957.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Adapters.KategoriDetailAdapter;
import com.gigs.maher1957.Fragments.OnFavClickListener;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.Models.Favorite.DataPostFav;
import com.gigs.maher1957.Models.Favorite.ResponsePostFav;
import com.gigs.maher1957.Models.KategoriDetail.DataKategoriDetail;
import com.gigs.maher1957.Models.KategoriDetail.ResponseKategoriDetail;
import com.gigs.maher1957.Models.KategoriDetail.ResultCategoryItem;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.R;

import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriDetailActivity extends AppCompatActivity implements OnItemClickListener {


    private DataKategoriDetail dataKategoriDetail;
    private List<ResultCategoryItem> resultCategoryItem;
    private KategoriDetailAdapter kategoriDetailAdapter;
    private RecyclerView recyclerView;
    private String token;
    private TinyDB tinyDB;
    private DataPostFav dataPostFav;
    private ImageView btnBack;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_detail);

        init();
    }

    private void init() {
        tinyDB = new TinyDB(KategoriDetailActivity.this);
        token = tinyDB.getString("token");
        String getSlug = getIntent().getStringExtra("slug");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewKategoriDetail);
        ButterKnife.bind(recyclerView);
        textView = (TextView) findViewById(R.id.textKategori);

        final ProgressDialog progressDialog = new ProgressDialog(KategoriDetailActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        RestClient.getService().getKategoriDetail(getSlug).enqueue(new Callback<ResponseKategoriDetail>() {
            @Override
            public void onResponse(Call<ResponseKategoriDetail> call, Response<ResponseKategoriDetail> response) {
                if(response.isSuccessful() && response.body() !=null){
                    progressDialog.dismiss();
                    dataKategoriDetail = response.body().getDataKategoriDetail();
                    resultCategoryItem = dataKategoriDetail.getResultCategory();
                    textView.setText("Category "+getSlug);
                    updateKategoriDetail(resultCategoryItem);
                }
            }

            @Override
            public void onFailure(Call<ResponseKategoriDetail> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

        btnBack = findViewById(R.id.btnBackKategori);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriDetailActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(KategoriDetailActivity.this);
                finish();
            }
        });
    }

    private void updateKategoriDetail(List<ResultCategoryItem> resultCategoryItem) {
        kategoriDetailAdapter = new KategoriDetailAdapter(resultCategoryItem,getBaseContext(),this);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(),2));
        recyclerView.setAdapter(kategoriDetailAdapter);
    }
//    @Override
//    public void onFavClick(int itemFav) {
//        int userID = tinyDB.getObject("user_id", Order.class).getId();
//
//        RestClient.getService().postFav("Bearer "+token,itemFav,userID).enqueue(new Callback<ResponsePostFav>() {
//            @Override
//            public void onResponse(Call<ResponsePostFav> call, Response<ResponsePostFav> response) {
//                if (response.isSuccessful() && response.body() != null){
//                    dataPostFav = response.body().getDataPostFav();
//                    Toast.makeText(KategoriDetailActivity.this, " Ditambahkan ke wishlist", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePostFav> call, Throwable t) {
//                Log.d("asd","fail");
//            }
//        });
//    }

    @Override
    public void onItemClick(int itemId) {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent showDetail = new Intent(KategoriDetailActivity.this, DetailActivity.class);
        showDetail.putExtra(DetailActivity.ITEM_ID,itemId);
        showDetail.putExtra(DetailActivity.PRODUCT_ID,itemId);
        startActivity(showDetail);
    }
}
