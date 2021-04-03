package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Models.Alamat.District.DetItem;
import com.gigs.maher1957.Models.Alamat.District.ResponseDistrict;
import com.gigs.maher1957.Models.Alamat.KotKab.DataKotKabID;
import com.gigs.maher1957.Models.Alamat.KotKab.KecItem;
import com.gigs.maher1957.Models.Alamat.KotKab.ResponseKotKabID;
import com.gigs.maher1957.Models.Alamat.Provinsi.CityItem;
import com.gigs.maher1957.Models.Alamat.Provinsi.DataProvinsi;
import com.gigs.maher1957.Models.Alamat.Provinsi.DataProvinsiID;
import com.gigs.maher1957.Models.Alamat.Provinsi.ResponseProvinsi;
import com.gigs.maher1957.Models.Alamat.Provinsi.ResponseProvinsiID;
import com.gigs.maher1957.Models.Alamat.ResponseAlamat;
import com.gigs.maher1957.Models.Alamat.ResponsePostAlamat;
import com.gigs.maher1957.Models.Alamat.Success;
import com.gigs.maher1957.Models.LoginRegister.Login.User;
import com.gigs.maher1957.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView isiProvinsi,isiKabKota,isiKecamatan,isiAlamat,isiKodePos;
    private EditText alamat, kodePos;
    private AutoCompleteTextView provinsi,kabkota,kecamatan;
    private Button btnSubmit;
    private ImageView btnBack;
    private TinyDB tinyDB;
    private Success detailsAlamat;
    private com.gigs.maher1957.Models.Alamat.District.Success detailsDistrict;
    private List<DataProvinsi> dataProvinsiList;
    private List<DataProvinsiID> dataProvinsiIDList;
    private List<DataKotKabID> dataKotKabIDList;
    private List<String> listKecamatan, listKotKab, listProvinsi;
    private int idKota;
    private int idProvinsi;
    private int idDistrict;
    private int districtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        init();
    }

    private void init() {
        tinyDB = new TinyDB(AlamatActivity.this);
        String token = tinyDB.getString("token");
        int userID = tinyDB.getObject("user_id", User.class).getId();

        provinsi = (AutoCompleteTextView) findViewById(R.id.provinsi);
        RestClient.getService().getProvinsi().enqueue(new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    dataProvinsiList = response.body().getData();
                    listProvinsi = new ArrayList<String>();
                    for (int i1 = 0; i1 < dataProvinsiList.size(); i1++){
                        listProvinsi.add(dataProvinsiList.get(i1).getName());
                    }

                    ArrayAdapter<String> provinsiAdapter = new ArrayAdapter<String>(AlamatActivity.this,android.R.layout.select_dialog_item,listProvinsi);
                    provinsi.setThreshold(1);
                    provinsi.setDropDownBackgroundResource(android.R.drawable.spinner_dropdown_background);
                    provinsi.setAdapter(provinsiAdapter);
//                    ArrayAdapter<String> provinsiAdapter = new ArrayAdapter<String>(AlamatActivity.this,R.layout.item_spinner,listProvinsi);
//                    provinsiAdapter.setDropDownViewResource(R.layout.item_spinner);
//                    provinsi.setAdapter(provinsiAdapter);
                } else {
                    Toast.makeText(AlamatActivity.this, "Failed to retrieve provincial data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Toast.makeText(AlamatActivity.this, "Internet connection problem", Toast.LENGTH_SHORT).show();
                Log.d("asd","fail");
            }
        });
        provinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedProvinsi = parent.getItemAtPosition(position).toString();

                for (int i2 = 0; i2 < listProvinsi.size(); i2++) {
                    if(dataProvinsiList.get(i2).getName().equals(selectedProvinsi))
                    {
                        idProvinsi = dataProvinsiList.get(i2).getId();
                        Toast.makeText(getBaseContext(),"Provinsi ID = " + idProvinsi,Toast.LENGTH_SHORT).show();
                        RestClient.getService().getProvinsiID(idProvinsi).enqueue(new Callback<ResponseProvinsiID>() {
                            @Override
                            public void onResponse(Call<ResponseProvinsiID> call, Response<ResponseProvinsiID> response) {
                                if (response.isSuccessful() && response.body() != null) {

                                    dataProvinsiIDList = response.body().getData();
                                    listKotKab = new ArrayList<String>();
                                    List<CityItem> listKota = new ArrayList<>();
                                    for (int i3 = 0; i3 < dataProvinsiIDList.size(); i3++) {
                                        listKota = dataProvinsiIDList.get(i3).getCity();
                                    }
                                    for (int j1 = 0; j1 < listKota.size(); j1++) {
                                        listKotKab.add(listKota.get(j1).getType()+" "+listKota.get(j1).getName());
                                    }

                                    ArrayAdapter<String> kotaAdapter = new ArrayAdapter<String>(AlamatActivity.this, android.R.layout.select_dialog_item, listKotKab);
                                    kabkota.setThreshold(1);
                                    kabkota.setDropDownBackgroundResource(android.R.drawable.spinner_dropdown_background);
                                    kabkota.setAdapter(kotaAdapter);
                                    List<CityItem> finalListKota = listKota;
                                    kabkota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            String selectedKota = parent.getItemAtPosition(position).toString();

                                            for (int i4 = 0; i4 < listKotKab.size(); i4++) {
                                                if ((finalListKota.get(i4).getType()+" "+finalListKota.get(i4).getName()).equals(selectedKota)) {

                                                    idKota = finalListKota.get(i4).getId();
                                                    Toast.makeText(getBaseContext(), "Kota/Kabupaten ID = " + idKota, Toast.LENGTH_SHORT).show();
                                                    RestClient.getService().getKotKabID(idKota).enqueue(new Callback<ResponseKotKabID>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseKotKabID> call, Response<ResponseKotKabID> response) {
                                                            if (response.isSuccessful() && response.body() != null) {

                                                                dataKotKabIDList = response.body().getData();
                                                                listKecamatan = new ArrayList<String>();
                                                                List<KecItem> listKecItem = new ArrayList<>();
                                                                for (int i5 = 0; i5 < dataKotKabIDList.size(); i5++) {
                                                                    listKecItem = dataKotKabIDList.get(i5).getKec();
                                                                }
                                                                for (int j2 = 0; j2 < listKecItem.size(); j2++) {
                                                                    listKecamatan.add(listKecItem.get(j2).getName());
                                                                }

                                                                ArrayAdapter<String> kotaAdapter = new ArrayAdapter<String>(AlamatActivity.this, android.R.layout.select_dialog_item, listKecamatan);
                                                                kecamatan.setThreshold(1);
                                                                kecamatan.setDropDownBackgroundResource(android.R.drawable.spinner_dropdown_background);
                                                                kecamatan.setAdapter(kotaAdapter);
                                                                List<KecItem> finalListKecItem = listKecItem;
                                                                kecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                        String selectedKecamatan = parent.getItemAtPosition(position).toString();

                                                                        for (int i6 = 0; i6 < listKecamatan.size(); i6++) {
                                                                            if (finalListKecItem.get(i6).getName().equals(selectedKecamatan)) {

                                                                                idDistrict = finalListKecItem.get(i6).getId();
                                                                                Toast.makeText(getBaseContext(), "District ID = " + idDistrict, Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                Toast.makeText(AlamatActivity.this, "Failed to retrieve District data", Toast.LENGTH_SHORT).show();
                                                                Log.d("asd", "FAIL");
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseKotKabID> call, Throwable t) {
                                                            Log.d("asd","fail");
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(AlamatActivity.this, "Failed to retrieve City / Regency data", Toast.LENGTH_SHORT).show();
                                    Log.d("asd", "FAIL");
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseProvinsiID> call, Throwable t) {
                                Log.d("asd","fail");
                            }
                        });
                    }
                }
            }
        });

        RestClient.getService().getAlamat("Bearer " + token, userID).enqueue(new Callback<ResponseAlamat>() {
            @Override
            public void onResponse(Call<ResponseAlamat> call, Response<ResponseAlamat> response) {
                if (response.isSuccessful() && response.body() != null){
                    detailsAlamat = response.body().getSuccess();
                    isiAlamat.setText(detailsAlamat.getAddress());
                    isiKodePos.setText(String.valueOf(detailsAlamat.getKodePos()));

                    districtID = detailsAlamat.getDistrictId();
                    RestClient.getService().getDistrict("Bearer " + token, districtID).enqueue(new Callback<ResponseDistrict>() {
                        @Override
                        public void onResponse(Call<ResponseDistrict> call, Response<ResponseDistrict> response) {
                            if(response.isSuccessful() && response.body() != null){
                                detailsDistrict = response.body().getSuccess();
                                for(DetItem detItem : detailsDistrict.getDet()){
                                    isiProvinsi.setText(detItem.getProvince().getName());
                                    isiKabKota.setText(detItem.getCity().getType()+" "+detItem.getCity().getName());
                                    isiKecamatan.setText(detItem.getName());
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

        kabkota = (AutoCompleteTextView) findViewById(R.id.kabkota);
        kecamatan = (AutoCompleteTextView) findViewById(R.id.kecamatan);

        btnBack = (ImageView) findViewById(R.id.btnBack);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        alamat = (EditText) findViewById(R.id.alamat);
        kodePos = (EditText) findViewById(R.id.kodePos);

        isiProvinsi = (TextView) findViewById(R.id.text_provinsi) ;
        isiKabKota = (TextView) findViewById(R.id.text_kabkota) ;
        isiKecamatan = (TextView) findViewById(R.id.text_kecamatan) ;
        isiAlamat = (TextView) findViewById(R.id.text_alamatlengkap) ;
        isiKodePos = (TextView) findViewById(R.id.text_kodePos);

        btnBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(AlamatActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(AlamatActivity.this);
                break;
            case R.id.btnSubmit:
                tinyDB = new TinyDB(AlamatActivity.this);
                String token = tinyDB.getString("token");
                int userID = tinyDB.getObject("user_id", User.class).getId();

                if (idDistrict == 0){
                    Toast.makeText(AlamatActivity.this, "District has not been filled", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String sProvinsi = provinsi.getText().toString();
                    isiProvinsi.setText(sProvinsi);
                    String sKabKota = kabkota.getText().toString();
                    isiKabKota.setText(sKabKota);
                    String sKecamatan = kecamatan.getText().toString();
                    isiKecamatan.setText(sKecamatan);
                    String sAlamat = alamat.getText().toString();
                    isiAlamat.setText(sAlamat);
                    String sKodePos = kodePos.getText().toString();
                    isiKodePos.setText(sKodePos);
                }

                RestClient.getService().postAlamat("Bearer " +token, userID, String.valueOf(idDistrict),alamat.getText().toString(),kodePos.getText().toString()).enqueue(new Callback<ResponsePostAlamat>() {
                    @Override
                    public void onResponse(Call<ResponsePostAlamat> call, Response<ResponsePostAlamat> response) {
                        if (response.isSuccessful() && response.body() != null){
                            Toast.makeText(AlamatActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePostAlamat> call, Throwable t) {
                        Log.d("asd","fail");
                    }
                });
        }
    }
}