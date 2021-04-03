package com.gigs.maher1957.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Models.Order.DataOrder;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.Models.Order.ResponseGetOrder;
import com.gigs.maher1957.R;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderActivity extends AppCompatActivity {
    private TextView invoiceID, date, status, paymen,shipped, subTotal, cost, grandTotal, note;
    private Button btnBayar;
    private ImageView btnBack;
    private TinyDB tinyDB;
    private DataOrder dataOrder;
    private Order order;
    private String notes;
    public static final String ORDER_ID = "order_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        init();
    }

    private void init() {

        tinyDB = new TinyDB(DetailOrderActivity.this);
        String token = tinyDB.getString("token");

        invoiceID = (TextView) findViewById(R.id.d1);
        date = (TextView) findViewById(R.id.d2);
        status = (TextView) findViewById(R.id.d3);
        paymen = (TextView) findViewById(R.id.d4);
        shipped = (TextView) findViewById(R.id.d5);
        subTotal = (TextView) findViewById(R.id.subTotal);
        cost = (TextView) findViewById(R.id.costShipping);
        grandTotal = (TextView) findViewById(R.id.grandTotal);
        note = findViewById(R.id.noteDetail);

        btnBayar = (Button) findViewById(R.id.btnBayarOrder);
        btnBack = (ImageView) findViewById(R.id.btnBackOrder);

//        recyclerViewBelanjaan = (RecyclerView) findViewById(R.id.recyclerViewOrder);
        int orderID = getIntent().getIntExtra(ORDER_ID,1);
        RestClient.getService().getOrder("Bearer " +token, orderID).enqueue(new Callback<ResponseGetOrder>() {
            @Override
            public void onResponse(Call<ResponseGetOrder> call, Response<ResponseGetOrder> response) {
                if(response.isSuccessful() && response.body() !=null){

                    Locale localeID = new Locale("in", "ID");
                    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                    formatRupiah.setMinimumFractionDigits(0);
                    formatRupiah.setMaximumFractionDigits(0);

                    dataOrder = response.body().getDataOrder();
                    order = response.body().getDataOrder().getOrder();
                    invoiceID.setText(order.getCode());
                    date.setText(order.getOrderDate());
                    status.setText(order.getStatus());
                    paymen.setText(order.getPaymentStatus());
                    shipped.setText(order.getShippingCourier());
                    cost.setText(formatRupiah.format((double)Integer.parseInt(order.getShippingCost())));
                    grandTotal.setText(formatRupiah.format((double)Integer.parseInt(order.getGrandTotal())));
                    subTotal.setText(formatRupiah.format((double)Integer.parseInt(order.getBaseTotalPrice())));
                    notes = order.getNote();
                    if(notes != null){
                        note.setText("Note : "+order.getNote());
                    } else {
                        note.setVisibility(View.INVISIBLE);
                    }

                    if(paymen.equals("unpaid")){
                        btnBayar.setVisibility(View.GONE);
                    } else{
                        btnBayar.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetOrder> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        DetailOrderActivity.this);

                alertDialog2.setTitle("Back?");
//                alertDialog2.setMessage("Jika anda kembali, maka pesanan akan disimpan");
                alertDialog2.setIcon(R.drawable.ic_arrow_left_24);
                alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Intent intent = new Intent(DetailOrderActivity.this, HomePageActivity.class);
                                Toast.makeText(DetailOrderActivity.this, "Your order is stored in orders", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                Animatoo.animateCard(DetailOrderActivity.this);
                                finish();
                            }
                        });
                alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog2.show();
            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailOrderActivity.this, PembayaranActivity.class);
                intent.setData(Uri.parse(order.getPaymentUrl()));
                startActivity(intent);
                Animatoo.animateSlideLeft(DetailOrderActivity.this);
                finish();
            }
        });

    }
}