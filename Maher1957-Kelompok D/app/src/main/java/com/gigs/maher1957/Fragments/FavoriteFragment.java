package com.gigs.maher1957.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.DetailActivity;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Adapters.FavoriteAdapter;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.Favorite.DataGetFav;
import com.gigs.maher1957.Models.Favorite.Product;
import com.gigs.maher1957.Models.Favorite.ResponseFavDelete;
import com.gigs.maher1957.Models.Favorite.ResponseGetFav;
import com.gigs.maher1957.R;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment implements OnItemClickListener,OnFavClickListener{

    public FavoriteFragment() {

    }
    private View view;
    private RecyclerView recyclerViewFavorite;
    private TinyDB tinyDB;
    private List<Product> products = new ArrayList<>();
    private FavoriteAdapter favoriteAdapter;
    private List<DataGetFav> dataGetFavs;
    private String token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_favorite, container, false);
        init();
        return view;
    }

    public void init(){

        tinyDB = new TinyDB(getContext());
        token = tinyDB.getString("token");

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        recyclerViewFavorite = (RecyclerView) view.findViewById(R.id.recyclerViewFavorite);
        ButterKnife.bind(recyclerViewFavorite);
        if (token.equals("")){
            Intent showDetail = new Intent(getContext(), EmailLoginActivity.class);
            startActivity(showDetail);
            Animatoo.animateCard(Objects.requireNonNull(getContext()));
            Toast.makeText(getContext(), "Silahkan login", Toast.LENGTH_SHORT).show();
        } else{
            RestClient.getService().getFav("Bearer " +token).enqueue(new Callback<ResponseGetFav>() {
                @Override
                public void onResponse(Call<ResponseGetFav> call, Response<ResponseGetFav> response) {
                    if (response.isSuccessful()&&response.body()!=null){
                        progressDialog.dismiss();
                        dataGetFavs = response.body().getData();
                        for (DataGetFav dataGetFav : dataGetFavs) {
                            Product product = new Product();
                            product.setName(dataGetFav.getProduct().getName());
                            product.setImgHover(dataGetFav.getProduct().getImgHover());
                            product.setDisc(dataGetFav.getProduct().getDisc());
                            product.setPrice(dataGetFav.getProduct().getPrice());
                            product.setPriceResult(dataGetFav.getProduct().getPriceResult());
                            product.setId(dataGetFav.getProduct().getId());
                            products.add(product);
                        }
                        UpdateUI(products);
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetFav> call, Throwable t) {
                    Log.d("asd","fail");
                }
            });
        }
//        Button btnBack = (Button) view.findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction btnBack = getFragmentManager().beginTransaction();
//                btnBack.replace(R.id.layout_favorite, new HomeFragment());
//                Animatoo.animateSlideLeft(Objects.requireNonNull(getContext()));
//                btnBack.commit();
//            }
//        });
    }

    private void UpdateUI(List<Product> data) {
        favoriteAdapter = new FavoriteAdapter(data,dataGetFavs,getContext(),this,this);
        recyclerViewFavorite.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewFavorite.setAdapter(favoriteAdapter);
        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int itemId) {
        Intent showDetail = new Intent(getContext(), DetailActivity.class);
        showDetail.putExtra(DetailActivity.ITEM_ID,itemId);
        showDetail.putExtra(DetailActivity.PRODUCT_ID,itemId);
        startActivity(showDetail);
    }

    @Override
    public void onFavClick(int itemFav) {
        RestClient.getService().deleteFav("Bearer "+token, itemFav).enqueue(new Callback<ResponseFavDelete>() {
            @Override
            public void onResponse(Call<ResponseFavDelete> call, Response<ResponseFavDelete> response) {
                if(response.isSuccessful() && response.body() !=null){
                    Toast.makeText(getContext(), "Dihapus dari wishlist", Toast.LENGTH_SHORT).show();
                    FragmentTransaction fav = getFragmentManager().beginTransaction();
                    fav.replace(R.id.layout_home, new FavoriteFragment());
                    fav.commit();
                }
            }

            @Override
            public void onFailure(Call<ResponseFavDelete> call, Throwable t) {
                Log.d("asd","fail");
            }
        });
    }
}
