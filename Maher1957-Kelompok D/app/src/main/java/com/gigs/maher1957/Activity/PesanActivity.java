package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Adapters.PesanAdapter;
import com.gigs.maher1957.Models.Alamat.District.DetItem;
import com.gigs.maher1957.Models.Alamat.District.ResponseDistrict;
import com.gigs.maher1957.Models.Alamat.ResponseAlamat;
import com.gigs.maher1957.Models.Alamat.Success;
import com.gigs.maher1957.Models.Checkout.DataCheckout;
import com.gigs.maher1957.Models.Checkout.DtCartitems;
import com.gigs.maher1957.Models.Checkout.ResponseCheckout;
import com.gigs.maher1957.Models.CheckoutOrder.DataCheckoutOrder;
import com.gigs.maher1957.Models.CheckoutOrder.ResponsePostCheckout;
import com.gigs.maher1957.Models.Kurir.DataKurir;
import com.gigs.maher1957.Models.Kurir.ResponseKurir;
import com.gigs.maher1957.Models.LoginRegister.Login.User;
import com.gigs.maher1957.Models.Pengiriman.CostItem;
import com.gigs.maher1957.Models.Pengiriman.CostsItem;
import com.gigs.maher1957.Models.Pengiriman.DataPengiriman;
import com.gigs.maher1957.Models.Pengiriman.ResponsePengiriman;
import com.gigs.maher1957.Models.Pengiriman.ResultsItem;
import com.gigs.maher1957.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnKupon,btnPesanan;
    private RecyclerView recyclerViewBelanjaan;
    private PesanAdapter pesanAdapter;
    private TextView provinsi, kotkab, kecamatan, alamat, kodepos, biayaPengiriman, subTotal, total, textBiaya, textEstimasi, textFrom, textTo;
    private EditText note;
    private Spinner jasaPengiriman, jenisLayanan;
    private TinyDB tinyDB;
    private Success detailsAlamat;
    private com.gigs.maher1957.Models.Alamat.District.Success detailsDistrict;
    private int districtID;
    private DataCheckout dataCheckout;
    private List<DtCartitems> dtCartitems;
    private List<DataKurir> dataKurirList;
    private List<String> listKurir;
    private String codeJasaPengiriman;
    private DataPengiriman dataPengiriman;
    private DataCheckoutOrder dataCheckoutOrder;
    private List<CostsItem> costsItems;
    private List<String> listCosts;
    private int value;
    private String etd;
    private String service;
    private String from;
    private String to;
    int totalAllPrice;
    int totalPrice;
    int totalPriceItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        init();
    }

    private void init() {

        tinyDB = new TinyDB(PesanActivity.this);
        String token = tinyDB.getString("token");
        int userID = tinyDB.getObject("user_id", User.class).getId();

        provinsi = (TextView) findViewById(R.id.provinsi_order);
        kotkab = (TextView) findViewById(R.id.kabkota_order);
        kecamatan = (TextView) findViewById(R.id.kecamatan_order);
        alamat = (TextView) findViewById(R.id.alamatlengkap_order);
        kodepos = (TextView) findViewById(R.id.kodePos_order);
        biayaPengiriman = (TextView) findViewById(R.id.biayaPengiriman);
        subTotal = (TextView) findViewById(R.id.subTotalProduct);
        total = (TextView) findViewById(R.id.totalPriceCheckout);
        textFrom = (TextView) findViewById(R.id.from);
        textTo = (TextView) findViewById(R.id.to);

        note = (EditText) findViewById(R.id.noteOrder);

        jasaPengiriman = (Spinner) findViewById(R.id.jasaPengiriman);
        jenisLayanan = (Spinner) findViewById(R.id.jenisLayanan);
        textBiaya = (TextView) findViewById(R.id.textBiaya);
        textEstimasi = (TextView) findViewById(R.id.textEstimasi);

        recyclerViewBelanjaan = (RecyclerView) findViewById(R.id.recyclerViewBelanjaan);

        RestClient.getService().getAlamat("Bearer " + token, userID).enqueue(new Callback<ResponseAlamat>() {
            @Override
            public void onResponse(Call<ResponseAlamat> call, Response<ResponseAlamat> response) {
                if (response.isSuccessful() && response.body() != null){
                    detailsAlamat = response.body().getSuccess();
                    alamat.setText(detailsAlamat.getAddress());
                    kodepos.setText(String.valueOf(detailsAlamat.getKodePos()));

                    districtID = detailsAlamat.getDistrictId();
                    RestClient.getService().getDistrict("Bearer " + token, districtID).enqueue(new Callback<ResponseDistrict>() {
                        @Override
                        public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                            if(response.isSuccessful() && response.body() != null){
                                detailsDistrict = response.body().getSuccess();
                                for(DetItem detItem : detailsDistrict.getDet()){
                                    provinsi.setText(detItem.getProvince().getName());
                                    kotkab.setText(detItem.getCity().getType()+" "+detItem.getCity().getName());
                                    kecamatan.setText(detItem.getName());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseDistrict> call, Throwable t) {
                            Log.d("asd","fail");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseAlamat> call, Throwable t) {
                Log.d("CREATION","failed");
            }
        });

        RestClient.getService().getCheckout("Bearer " +token).enqueue(new Callback<ResponseCheckout>() {
            @Override
            public void onResponse(Call<ResponseCheckout> call, Response<ResponseCheckout> response) {
                if(response.isSuccessful() && response.body() != null) {
                    dataCheckout = response.body().getDataCheckout();
                    dtCartitems = response.body().getDataCheckout().getDtCartitems();
                    UpdateDataCart(dtCartitems);
                }
            }
            @Override
            public void onFailure(Call<ResponseCheckout> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

        RestClient.getService().getKurir().enqueue(new Callback<ResponseKurir>() {
            @Override
            public void onResponse(Call<ResponseKurir> call, Response<ResponseKurir> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    dataKurirList = response.body().getData();
                    listKurir = new ArrayList<String>();
                    for (int i = 0; i < dataKurirList.size(); i++){
                        listKurir.add(dataKurirList.get(i).getTitle());
                    }
                    ArrayAdapter<String> kurirAdapter = new ArrayAdapter<String>(PesanActivity.this,R.layout.item_spinner,listKurir);
                    kurirAdapter.setDropDownViewResource(R.layout.item_spinner);
                    jasaPengiriman.setAdapter(kurirAdapter);
                } else {
                    Toast.makeText(PesanActivity.this, "Failed to retrieve provincial data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKurir> call, Throwable t) {

            }
        });
        jasaPengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedJasaPengiriman = parent.getItemAtPosition(position).toString();

                for (int i2 = 0; i2 < listKurir.size(); i2++) {
                    if (dataKurirList.get(i2).getTitle().equals(selectedJasaPengiriman)) {
                        codeJasaPengiriman = dataKurirList.get(i2).getCode();
                        RestClient.getService().postPengiriman("Bearer " +token, districtID, dataCheckout.getWeight(), codeJasaPengiriman).enqueue(new Callback<ResponsePengiriman>() {
                            @Override
                            public void onResponse(Call<ResponsePengiriman> call, Response<ResponsePengiriman> response) {
                                if(response.isSuccessful() && response.body() !=null){
                                    dataPengiriman = response.body().getDataPengiriman();
                                    for(ResultsItem resultsItem : dataPengiriman.getArrayResult().getRajaongkir().getResults()){
                                        costsItems = resultsItem.getCosts();
                                    }
                                    listCosts = new ArrayList<>();
                                    for (int l = 0; l < costsItems.size(); l++){
                                        listCosts.add(costsItems.get(l).getService());
                                    }
                                    textFrom.setText(dataPengiriman.getOrigin());
                                    textTo.setText(dataPengiriman.getDestination());
                                    ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(PesanActivity.this,R.layout.item_spinner,listCosts);
                                    serviceAdapter.setDropDownViewResource(R.layout.item_spinner);
                                    jenisLayanan.setAdapter(serviceAdapter);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponsePengiriman> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        jenisLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                formatRupiah.setMinimumFractionDigits(0);
                formatRupiah.setMaximumFractionDigits(0);

                String selectedJenisLayanan = parent.getItemAtPosition(position).toString();

                for (int p = 0; p < listCosts.size(); p++){
                    service = costsItems.get(p).getService();
                    if (costsItems.get(p).getService().equals(selectedJenisLayanan)){
                        for(CostItem costItem : costsItems.get(p).getCost() ){
                            value = costItem.getValue();
                            etd = costItem.getEtd();
                        }
                    }
                    textBiaya.setText(formatRupiah.format((double)value));
                    textEstimasi.setText(etd);
                    biayaPengiriman.setText(formatRupiah.format((double)value));
                    totalAllPrice = totalPrice + value;

                    total.setText(String.valueOf(totalAllPrice));
                    total.setText(formatRupiah.format((double) totalAllPrice));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnKupon = (Button) findViewById(R.id.btnKupon);
        btnKupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PesanActivity.this, KuponActivity.class);
//                startActivity(intent);
//                Animatoo.animateZoom(PesanActivity.this);
                Toast.makeText(PesanActivity.this, "Kupon Tidak Tersedia", Toast.LENGTH_SHORT).show();
            }
        });
        btnPesanan = (Button) findViewById(R.id.btnCheckCostShipping);
        if (note.getText().toString() == ""){
            note.getText();
        }
        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient.getService().postCheckout("Bearer "+ token, value, codeJasaPengiriman, service, note.getText().toString()).enqueue(new Callback<ResponsePostCheckout>() {
                    @Override
                    public void onResponse(Call<ResponsePostCheckout> call, Response<ResponsePostCheckout> response) {
                        if(response.isSuccessful() && response.body() !=null){
                            dataCheckoutOrder = response.body().getDataCheckoutOrder();

                            Intent intent = new Intent(PesanActivity.this, DetailOrderActivity.class);
                            intent.putExtra(DetailOrderActivity.ORDER_ID,dataCheckoutOrder.getId());
                            startActivity(intent);
                            Animatoo.animateSlideLeft(PesanActivity.this);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePostCheckout> call, Throwable t) {
                        Toast.makeText(PesanActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PesanActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(PesanActivity.this);
                finish();
            }
        });
    }

    private void UpdateDataCart(List<DtCartitems> dtCartitems) {
        pesanAdapter = new PesanAdapter(dtCartitems,PesanActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PesanActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewBelanjaan.setLayoutManager(layoutManager);
        recyclerViewBelanjaan.setAdapter(pesanAdapter);
        pesanAdapter.notifyDataSetChanged();

        calculateData();
    }

    private void calculateData() {
        totalPriceItem = 0;
        totalPrice = 0;
        for (int i = 0; i < dataCheckout.getDtCartitems().size(); i++){
            totalPriceItem = ((Integer.parseInt(dataCheckout.getDtCartitems().get(i).getPrice()))) * dataCheckout.getDtCartitems().get(i).getQty();
            totalPrice = totalPrice + totalPriceItem ;
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        subTotal.setText(String.valueOf(totalPrice));
        subTotal.setText(formatRupiah.format((double) totalPrice));

    }
}