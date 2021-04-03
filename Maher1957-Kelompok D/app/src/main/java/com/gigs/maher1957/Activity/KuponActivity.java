package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.Adapters.KuponAdapter;
import com.gigs.maher1957.Models.ListModel;
import com.gigs.maher1957.R;
import java.util.ArrayList;
import java.util.List;

public class KuponActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewKupon;
    private KuponAdapter kuponAdapter;
    private List<ListModel> listModelList;
    private Button btnAktif;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupon);

        init();
    }

    private void init() {

        recyclerViewKupon = (RecyclerView) findViewById(R.id.recyclerViewKupon);
        LinearLayoutManager layoutManagerNotifikasi = new LinearLayoutManager(this);
        layoutManagerNotifikasi.setOrientation(RecyclerView.VERTICAL);
        recyclerViewKupon.setLayoutManager(layoutManagerNotifikasi);

        listModelList = new ArrayList<>();
//        listModelList.add(new ListModel(R.drawable.ic_discount,"Discount 5%","Nikmati Promo MaherOutfit harga mulai Rp 100.000 dan diskon 5% berlaku untuk minumun belanja Rp 300.000. Aktifkan Sekarang! ","19-06-2020 17.30"));
//        listModelList.add(new ListModel(R.drawable.ic_discount,"Discount 30%","Nikmati Promo MaherOutfit harga mulai Rp 50.000 dan diskon 30% berlaku untuk minumun belanja Rp 150.000. Aktifkan Sekarang!","19-06-2020 17.30"));
//        listModelList.add(new ListModel(R.drawable.ic_discount,"Discount 15%","Nikmati Promo MaherOutfit harga mulai Rp 70.000 dan diskon 15% berlaku untuk minumun belanja Rp 450.000. Aktifkan Sekarang!","19-06-2020 17.30"));

        kuponAdapter = new KuponAdapter(listModelList,this);
        recyclerViewKupon.setAdapter(kuponAdapter);
        kuponAdapter.notifyDataSetChanged();

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(KuponActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(KuponActivity.this);
                finish();

        }
    }
}