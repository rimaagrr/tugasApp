package com.gigs.maher1957.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.AlamatActivity;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.KuponActivity;
import com.gigs.maher1957.Activity.PasswordActivity;
import com.gigs.maher1957.Activity.ProfileActivity;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.CartBaru.Data;
import com.gigs.maher1957.Models.CartBaru.DataItemCart;
import com.gigs.maher1957.Models.CartBaru.ResponseGetCartBaru;
import com.gigs.maher1957.Models.Kategori.ResponseKategori;
import com.gigs.maher1957.Models.LoginRegister.Login.User;
import com.gigs.maher1957.Models.Sosmed.DataSosmed;
import com.gigs.maher1957.Models.Sosmed.ResponseSosmed;
import com.gigs.maher1957.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment implements View.OnClickListener {

    public UserFragment() {

    }

    private String token;
    private Object verifikasi;
    private TinyDB tinyDB;
    private View view;
    private TextView Profile, Alamat, Kupon, Password, Keluar;
    DrawerLayout drawerLayoutUser;
    NavigationView navigationViewUser;
    Activity context;
    private List<DataSosmed> dataSosmeds;
    String list;
    private Data dataCart;
    private List<DataItemCart> itemCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);
        context = getActivity();
        onSetNavigationDrawerEvents();
        return view;
    }

    private void onSetNavigationDrawerEvents() {
        tinyDB = new TinyDB(getContext());
        token = tinyDB.getString("token");

        drawerLayoutUser = (DrawerLayout) view.findViewById(R.id.drawerlayoutUser);
        navigationViewUser = (NavigationView) view.findViewById(R.id.navigationViewUser);

        Profile = (TextView) view.findViewById(R.id.text_profile);
        Kupon = (TextView) view.findViewById(R.id.text_kupon);
        Alamat = (TextView) view.findViewById(R.id.text_alamat);
        Password = (TextView) view.findViewById(R.id.text_password);
        Keluar = (TextView) view.findViewById(R.id.text_keluar);
        if (token.equals("")) {
            Keluar.setText("Login");
        } else{
            Keluar.setText("Logout");
        }
//        Informasi = (TextView) view.findViewById(R.id.text_informasi);
//        Instagram = (TextView) view.findViewById(R.id.text_instagram);
        Profile.setOnClickListener(this);
        Kupon.setOnClickListener(this);
        Alamat.setOnClickListener(this);
        Password.setOnClickListener(this);
        Keluar.setOnClickListener(this);

//        Informasi.setOnClickListener(this);
//        Instagram.setOnClickListener(this);
//        RestClient.getService().getSosmed().enqueue(new Callback<ResponseSosmed>() {
//            @Override
//            public void onResponse(Call<ResponseSosmed> call, Response<ResponseSosmed> response) {
//                if(response.isSuccessful() && response.body()!=null){
//                    dataSosmeds = response.body().getData();
//                    list = "";
//                    for (int i = 0; i < dataSosmeds.size(); i++){
//                        list = dataSosmeds.get(i).getLink();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseSosmed> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_profile:
                if (token.equals("")){
                    Toast.makeText(context, "Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), EmailLoginActivity.class));
                    Animatoo.animateCard(Objects.requireNonNull(getContext()));
                } else{
                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                }
                break;

            case R.id.text_alamat:
                if (token.equals("") ){
                    Toast.makeText(context, "Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), EmailLoginActivity.class));
                    Animatoo.animateCard(Objects.requireNonNull(getContext()));
                } else{
                    startActivity(new Intent(getActivity(), AlamatActivity.class));
                }
                break;

            case R.id.text_password:
                if (token.equals("")){
                    Toast.makeText(context, "Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), EmailLoginActivity.class));
                    Animatoo.animateCard(Objects.requireNonNull(getContext()));
                } else{
                    startActivity(new Intent(getActivity(), PasswordActivity.class));
                }
                break;
            case R.id.text_keluar:
                tinyDB.remove("token");

                Intent intent = new Intent(getContext(), EmailLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                break;

            case R.id.text_kupon:
                if (token.equals("")){
                    Toast.makeText(context, "Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), EmailLoginActivity.class));
                    Animatoo.animateCard(Objects.requireNonNull(getContext()));
                } else{
                    startActivity(new Intent(getActivity(), KuponActivity.class));
                }
                break;

//            case R.id.text_informasi:
//                Toast.makeText(getContext(), "About Us", Toast.LENGTH_SHORT).show();
//                break;

//            case R.id.text_instagram:
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(list));
//                startActivity(intent);
//                break;
        }
    }

}
