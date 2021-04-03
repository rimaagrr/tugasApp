package com.gigs.maher1957.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.DetailActivity;
import com.gigs.maher1957.Activity.DetailOrderActivity;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.PembayaranActivity;
import com.gigs.maher1957.Activity.PesanActivity;
import com.gigs.maher1957.Adapters.NotifikasiAdapter;
import com.gigs.maher1957.Models.CartBaru.Data;
import com.gigs.maher1957.Models.CartBaru.DataItemCart;
import com.gigs.maher1957.Models.CartBaru.ResponseGetCartBaru;
import com.gigs.maher1957.Models.Notifikasi.DataListOrder;
import com.gigs.maher1957.Models.Notifikasi.OrdersItem;
import com.gigs.maher1957.Models.Notifikasi.ResponseListOrder;
import com.gigs.maher1957.Models.Order.DataOrder;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.R;

import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment implements OnListOrderClickListener {

    public NotificationFragment() {

    }
    private View view;
    private TextView textNotifikasi;
    private RecyclerView recyclerViewNotifikasi;
    private NotifikasiAdapter notifikasiAdapter;
    private TinyDB tinyDB;
    private DataListOrder dataListOrder;
    private List<OrdersItem> ordersItemList;
    private Data dataCart;
    private List<DataItemCart> itemCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification, container, false);

        init();
        return view;
    }

    private void init() {
        tinyDB = new TinyDB(getContext());
        String token = tinyDB.getString("token");
        textNotifikasi = view.findViewById(R.id.textNotifikasi);
        textNotifikasi.setText("Empty");

        RestClient.getService().getCartBaru("Bearer " + token).enqueue(new Callback<ResponseGetCartBaru>() {
            @Override
            public void onResponse(Call<ResponseGetCartBaru> call, Response<ResponseGetCartBaru> response) {
                if(response.isSuccessful() && response.body() != null){
                    dataCart = response.body().getData();
                    itemCart = response.body().getData().getDataItemCart();
                    try {
                        ((HomePageActivity) Objects.requireNonNull(getContext())).setBadgeCart(dataCart.getCountCart());
                    } catch(Exception data){
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetCartBaru> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        ///// produk model list //////
        recyclerViewNotifikasi = (RecyclerView) view.findViewById(R.id.recyclerViewNotifikasi);
        ButterKnife.bind(recyclerViewNotifikasi);
        RestClient.getService().getListOrder("Bearer " +token).enqueue(new Callback<ResponseListOrder>() {
            @Override
            public void onResponse(Call<ResponseListOrder> call, Response<ResponseListOrder> response) {
                if(response.isSuccessful() && response.body() !=null){
                    progressDialog.dismiss();
                    dataListOrder = response.body().getDataListOrder();
                    ordersItemList = response.body().getDataListOrder().getOrders();

                    setTextNotifikasi(dataListOrder.getOrdersCount());

                    updateNotif(response.body().getDataListOrder().getOrders());
                }
            }

            @Override
            public void onFailure(Call<ResponseListOrder> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

//        int orderID = Objects.requireNonNull(getActivity()).getIntent().getIntExtra(ORDER_ID,141);
//        RestClient.getService().getOrder("Bearer " +token, orderID).enqueue(new Callback<ResponseGetOrder>() {
//            @Override
//            public void onResponse(Call<ResponseGetOrder> call, Response<ResponseGetOrder> response) {
//                if(response.isSuccessful() && response.body() !=null){
//                    dataOrder = response.body().getDataOrder();
//                    order = response.body().getDataOrder().getOrder();
//                    List<Order> orderList = new ArrayList<>();
//                    orderList.add(dataOrder.getOrder());
//                    updateNotif(orderList);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetOrder> call, Throwable t) {
//                Log.d("asd","fail");
//            }
//        });

    }

    private void updateNotif(List<OrdersItem> order) {

        notifikasiAdapter = new NotifikasiAdapter(order,getContext(),this);
        LinearLayoutManager layoutManagerNotifikasi = new LinearLayoutManager(getContext());
        layoutManagerNotifikasi.setOrientation(RecyclerView.VERTICAL);
        recyclerViewNotifikasi.setLayoutManager(layoutManagerNotifikasi);
        recyclerViewNotifikasi.setAdapter(notifikasiAdapter);
        notifikasiAdapter.notifyDataSetChanged();
    }

    @Override public void onListOrderClick(int orderID) {
//        Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getActivity(), PesanActivity.class));
        ///// fragment to activity //////
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getContext());

        alertDialog2.setTitle("Order Details");
        alertDialog2.setMessage("Check your order details?");
//        alertDialog2.setIcon(R.drawable.ic_arrow_left_24);
        alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent showDetail = new Intent(getActivity(), DetailOrderActivity.class);
                showDetail.putExtra(DetailOrderActivity.ORDER_ID,orderID);
                Animatoo.animateSlideLeft(Objects.requireNonNull(getContext()));
                startActivity(showDetail);
            }
        });
        alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog2.show();
    }
    public void setTextNotifikasi(int countOrder) {
        if (countOrder == 0) {
            textNotifikasi.setVisibility(View.VISIBLE);
        } else {
            textNotifikasi.setVisibility(View.GONE);
        }
    }
}
