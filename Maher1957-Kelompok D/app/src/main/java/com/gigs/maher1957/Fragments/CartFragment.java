package com.gigs.maher1957.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.DetailOrderActivity;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.PesanActivity;
import com.gigs.maher1957.Adapters.CartAdapter;
import com.gigs.maher1957.Adapters.CartAdapterV2;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Helper.RecycleItemTouchHelper;
import com.gigs.maher1957.Helper.RecycleItemTouchHelperListener;
import com.gigs.maher1957.Models.CartBaru.Data;
import com.gigs.maher1957.Models.CartBaru.DataItemCart;
import com.gigs.maher1957.Models.CartBaru.ResponseDeleteCartBaru;
import com.gigs.maher1957.Models.CartBaru.ResponseGetCartBaru;
import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.R;
import com.gigs.maher1957.SwipeToDelete;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    public CartFragment() {
    }

    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    private TinyDB tinyDB;
    private View view;
    private Button btnCheckOut;
    private TextView totalPriceCart, textCart;
    private CoordinatorLayout root;
    private RecyclerView recyclerViewCart;
    private CartAdapterV2 cartAdapterV2;
    private ArrayList<DataItem> dataItemArrayList;
    private Data dataCart;
    private List<DataItemCart> itemCart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String token;
    private Snackbar snackbar;
    private RecyclerView.ViewHolder viewHolder;
    int totalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);

        init();
        return view;
    }

    private void init() {

        dataCart = new Data();
        recyclerViewCart = (RecyclerView) view.findViewById(R.id.recyclerViewCart);
        ButterKnife.bind(recyclerViewCart);
        tinyDB = new TinyDB(getContext());

//        dataItemArrayList = new ArrayList<>();
//        if (tinyDB.getListObject("cart",DataItem.class) != null){
//           for (Object object : tinyDB.getListObject("cart",DataItem.class)){
//               DataItem dataItem = (DataItem) object;
//               dataItemArrayList.add(dataItem);
//           }
//        }

        ///// Button /////
        btnCheckOut = (Button) view.findViewById(R.id.btnCheckOut);
        btnCheckOut.setOnClickListener(this);

        totalPriceCart = view.findViewById(R.id.totalPriceCart);
        textCart = view.findViewById(R.id.textCart);
        textCart.setText("Cart Empty");
        root = view.findViewById(R.id.layout_cart);

//        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecycleItemTouchHelper(0, ItemTouchHelper.LEFT,this);
//        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerViewCart);

        getCart();
//        swipe();
    }


    private void getCart() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setContentView(R.layout.progres_dialog);

        token = tinyDB.getString("token");

        RestClient.getService().getCartBaru("Bearer " + token).enqueue(new Callback<ResponseGetCartBaru>() {
            @Override
            public void onResponse(Call<ResponseGetCartBaru> call, Response<ResponseGetCartBaru> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();

                    dataCart = response.body().getData();
                    itemCart = response.body().getData().getDataItemCart();

                    setTextCart(dataCart.getCountCart());
//                    try {
//                        ((HomePageActivity) getContext()).setBadgeCart(dataCart.getCountCart());
//
//                    } catch(Exception data){
//
//                    }
                    updateItem(response.body().getData().getDataItemCart());

                }
            }

            @Override
            public void onFailure(Call<ResponseGetCartBaru> call, Throwable t) {
                Log.d("asd", "fail");
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.refreshCart);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                init();
                Intent showDetail = new Intent(getContext(), HomePageActivity.class);
                startActivity(showDetail);
            }
        });
        swipeRefreshLayout.setRefreshing(false);

//        RestClient.getService().getCartV2("Bearer " + token).enqueue(new Callback<ResponseGetCartv2>() {
//            @Override
//            public void onResponse(Call<ResponseGetCartv2> call, Response<ResponseGetCartv2> response) {
//                if(response.isSuccessful() && response.body() != null){
//                    progressDialog.dismiss();
//                    dataGetCartList = response.body().getDataGetCart();
//                    UpdateCart(dataGetCartList.getDtCartitems());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetCartv2> call, Throwable t) {
//                Log.d("asd","fail");
//            }
//        });

//        if (tinyDB.getObject("cartToken", ResponseTokenCart.class)==null){
//            RestClient.getService().getTokenCart("Bearer " + token).enqueue(new Callback<ResponseTokenCart>() {
//                @Override
//                public void onResponse(Call<ResponseTokenCart> call, Response<ResponseTokenCart> response) {
//                    if(response.isSuccessful() && response.body() != null){
//                        tinyDB.putObject("cartToken",response.body());
//                    } else {
//                        Log.d("asd","response");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseTokenCart> call, Throwable t) {
//                    Log.d("asd","fail");
//                }
//            });
//        } else {
//            ResponseCartToken responseCartToken = tinyDB.getObject("cartToken",ResponseCartToken.class);
//            RestClient.getService().getCarts("Bearer " + token, responseCartToken.getCartToken(), responseCartToken.getCartKey()).enqueue(new Callback<ResponseGetCart>() {
//                @Override
//                public void onResponse(Call<ResponseGetCart> call, Response<ResponseGetCart> response) {
//                    if(response.isSuccessful() && response.body() != null) {
//                        cartList = response.body().getItemsInCart();
//                        updateItem(response.body().getItemsInCart());
//                    } else {
//                        RestClient.getService().getCartToken("Bearer " + token).enqueue(new Callback<ResponseCartToken>() {
//                            @Override
//                            public void onResponse(Call<ResponseCartToken> call, Response<ResponseCartToken> response) {
//                                if(response.isSuccessful() && response.body() != null){
//                                    tinyDB.putObject("cartToken",response.body());
//                                    getCartV2();
//                                } else {
//                                    Log.d("asd","response");
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseCartToken> call, Throwable t) {
//                                Log.d("asd","fail");
//                            }
//                        });
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseGetCart> call, Throwable t) {
//                    Log.d("asd","fail2");
//                }
//            });
//        }
    }

    private void updateItem(List<DataItemCart> dataItemCarts) {
        cartAdapterV2 = new CartAdapterV2(dataItemCarts, getContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCart.setLayoutManager(layoutManager);
        recyclerViewCart.setAdapter(cartAdapterV2);
        cartAdapterV2.notifyDataSetChanged();

        calculateData();
    }


//    private void UpdateCart(List<DtCartitemsItem> dtCartitems) {
//        cartAdapterV2 = new CartAdapterV2(dtCartitems,getContext(),this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerViewCart.setLayoutManager(layoutManager);
//        recyclerViewCart.setAdapter(cartAdapterV2);
//        cartAdapterV2.notifyDataSetChanged();
//
//        calculateData();
//    }


//    private void updateItem(List<ItemsInCartItem> itemsInCart) {
//        cartAdapter = new CartAdapter(itemsInCart,getContext(),this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerViewCart.setLayoutManager(layoutManager);
//        recyclerViewCart.setAdapter(cartAdapter);
//        cartAdapter.notifyDataSetChanged();
//
//        calculateData();
//    }

    private void calculateData() {
        totalPrice = 0;
        int totalPriceItem = 0;
        for (int i = 0; i < dataCart.getDataItemCart().size(); i++) {
            totalPriceItem = ((Integer.parseInt(dataCart.getDataItemCart().get(i).getPrice()))) * dataCart.getDataItemCart().get(i).getQty();
            totalPrice = totalPrice + totalPriceItem;
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        totalPriceCart.setText(String.valueOf(totalPrice));
        totalPriceCart.setText(formatRupiah.format((double) totalPrice));
    }

    //    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            int position = viewHolder.getAdapterPosition();
//
//            switch (direction) {
//                case ItemTouchHelper.LEFT:
//                    dataItemArrayList = cartModelList.get(position);
//                    cartModels.add(cart);
//                    cartModelList.remove(position);
//                    cartAdapter.notifyItemRemoved(position);
//                    Snackbar.make(recyclerViewCart, name + ", Dihapus", Snackbar.LENGTH_LONG)
//                            .setAction("Batalkan", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    cartModels.remove(cartModels.lastIndexOf(cart));
//                                    cartModelList.add(position, cart);
//                                    cartAdapter.notifyItemInserted(position);
//                                }
//                            }).show();
//                    break;
//            }
//        }
//
//        @Override
//        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//
//            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
//                    .addActionIcon(R.drawable.ic_delete_24)
//                    .create()
//                    .decorate();
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    };
    public void setTextCart(int countCart) {
        if (countCart == 0) {
            textCart.setVisibility(View.VISIBLE);
        } else {
            textCart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (totalPrice == 0) {
            Toast.makeText(getContext(), "Cart Empty", Toast.LENGTH_SHORT).show();
        } else {
            if (v.getId() == R.id.btnCheckOut) {
                startActivity(new Intent(getActivity(), PesanActivity.class));
            }
        }
    }

    @Override
    public void onItemClick(int itemId) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getContext());
        alertDialog2.setTitle("Delete");
        alertDialog2.setMessage("Delete Product?");
        alertDialog2.setIcon(R.drawable.ic_delete_24);
        alertDialog2.setCancelable(false);
        alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog
                RestClient.getService().delCartBaru("Bearer " + token, itemId).enqueue(new Callback<ResponseDeleteCartBaru>() {
                    @Override
                    public void onResponse(Call<ResponseDeleteCartBaru> call, Response<ResponseDeleteCartBaru> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(getContext(), "Product deleted successfully. Please Refresh", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseDeleteCartBaru> call, Throwable t) {
                        Log.d("asd", "fail");
                    }
                });
            }
        });
        alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog2.show();
    }
}
//        SwipeToDelete swipeToDeleteCallback = new SwipeToDelete(getContext()) {
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//                final int deleteIndex = viewHolder.getAdapterPosition();
//                DataItemCart dataItems  = itemCart.get(viewHolder.getAdapterPosition());
//                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
//                        getContext());
//
//                alertDialog2.setTitle("Hapus");
//                alertDialog2.setMessage("Apakah anda yakin?");
//                alertDialog2.setIcon(R.drawable.ic_delete_24);
//                alertDialog2.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Write your code here to execute after dialog
//                        RestClient.getService().delCartBaru("Bearer " + token,itemId).enqueue(new Callback<ResponseDeleteCartBaru>() {
//                            @Override
//                            public void onResponse(Call<ResponseDeleteCartBaru> call, Response<ResponseDeleteCartBaru> response) {
//                                if(response.isSuccessful() && response.body()!=null){
//                                    String message = response.body().getMessage();
//                                    cartAdapterV2.removeItem(deleteIndex);
//                                    snackbar = Snackbar
//                                            .make(root, ""+message, Snackbar.LENGTH_LONG);
////                snackbar.setAction("UNDO", new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////
////                        cartAdapterV2.restoreItem(dataItems,deleteIndex);
////                        recyclerViewCart.scrollToPosition(i);
////                    }
////                });
//
////                snackbar.setActionTextColor(Color.YELLOW);
//                                    snackbar.show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseDeleteCartBaru> call, Throwable t) {
//                                Log.d("asd","fail");
//                            }
//                        });
//                    }
//                });
//                alertDialog2.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                        cartAdapterV2.restoreItem(dataItems,deleteIndex);
//                        recyclerViewCart.scrollToPosition(i);
//                    }
//                });
//                alertDialog2.show();
//
//            }
//        };
//
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
//        itemTouchhelper.attachToRecyclerView(recyclerViewCart);

//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
//        if (viewHolder instanceof CartAdapter.CartViewHolder){
//            String name = itemCart.get(viewHolder.getAdapterPosition()).getProductName();
//
//            DataItemCart dataItems  = itemCart.get(viewHolder.getAdapterPosition());
//            int deleteIndex = viewHolder.getAdapterPosition();
//
//            cartAdapterV2.removeItem(deleteIndex);
//
//            Snackbar snackbar = Snackbar.make(root,name + " dihapus dari cart!", Snackbar.LENGTH_LONG);
//            snackbar.setAction("Batalkan", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    cartAdapterV2.restoreItem(dataItems,deleteIndex);
//                    recyclerViewCart.scrollToPosition(position);
//                }
//            });
//            snackbar.setActionTextColor(Color.RED);
//            snackbar.show();
//        }
//    }
//    private void swipe() {
//
//    }


