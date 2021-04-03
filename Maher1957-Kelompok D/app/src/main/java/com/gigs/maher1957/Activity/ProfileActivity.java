package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.Models.Profile.ResponseDetailsProfile;
import com.gigs.maher1957.Models.Profile.ResponsePostProfile;
import com.gigs.maher1957.Models.Profile.Success;
import com.gigs.maher1957.Models.Profile.UserProfile;
import com.gigs.maher1957.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image,btnBack;
    private Button btnLogout,btnSave;
    private TinyDB tinyDB;
    private Success detailsProfile;
    private UserProfile userProfile;
    private EditText namaUser, nama, email, phone;
    public static final String USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
    }

    private void init() {
        userProfile = new UserProfile();

        TinyDB tinyDB = new TinyDB(ProfileActivity.this);
        String token = tinyDB.getString("token");
        int userID = tinyDB.getObject("user_id", Order.class).getId();
        Log.d("CREATION",tinyDB.getString("token"));

        RestClient.getService().getProfile("Bearer " + token,userID).enqueue(new Callback<ResponseDetailsProfile>() {
            @Override
            public void onResponse(Call<ResponseDetailsProfile> call, Response<ResponseDetailsProfile> response) {
                Log.d("CREATION","asd");
                if (response.isSuccessful()&&response.body()!=null){
                    detailsProfile = response.body().getSuccess();
                    UpdateProfile(response.body().getSuccess());
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailsProfile> call, Throwable t) {
                Log.d("CREATION","failed");
            }
        });

        namaUser = (EditText) findViewById(R.id.namaUser);
        nama = (EditText) findViewById(R.id.nama);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        image = (ImageView) findViewById(R.id.image);
        Glide.with(getBaseContext()).load(R.drawable.ic_profile).apply(RequestOptions.circleCropTransform()).into(image);

        btnBack = (ImageView) findViewById(R.id.btnBack);
//        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSave = (Button) findViewById(R.id.save);

        btnBack.setOnClickListener(this);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tinyDB.remove("token");
//
//                Intent intent = new Intent(ProfileActivity.this, EmailLoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("EXIT", true);
//                startActivity(intent);
//            }
//        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient.getService().postProfile("Bearer " + token, userID, nama.getText().toString(), namaUser.getText().toString(), email.getText().toString() , phone.getText().toString()).enqueue(new Callback<ResponsePostProfile>() {
                    @Override
                    public void onResponse(Call<ResponsePostProfile> call, Response<ResponsePostProfile> response) {
                        if (response.isSuccessful() && response.body() != null){
                            nama.setText(response.body().getName());
                            namaUser.setText(response.body().getUsername());
                            email.setText(response.body().getEmail());
                            phone.setText(response.body().getPhone());
                            Toast.makeText(ProfileActivity.this, "Changes are saved", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePostProfile> call, Throwable t) {
                        Log.d("sad","fail");
                    }
                });
            }
        });
    }

    private void UpdateProfile(Success success) {
        namaUser.setText(success.getUserProfile().getUsername());
        nama.setText(success.getUserProfile().getName());
        email.setText(success.getUserProfile().getEmail());
        phone.setText(success.getUserProfile().getPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(ProfileActivity.this);
                break;
        }
    }
}