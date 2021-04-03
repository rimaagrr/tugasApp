package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Models.CartBaru.DataItemCart;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapterV2 extends RecyclerView.Adapter<CartAdapterV2.CartViewHolder> {

    private List<DataItemCart> itemsCart;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int overTotalPrice = 0;
    private DataItemCart item;

    public CartAdapterV2(List<DataItemCart> itemsCart, Context context, OnItemClickListener onItemClickListener) {
        this.itemsCart = itemsCart;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CartAdapterV2.CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart,viewGroup,false);
        return new CartAdapterV2.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterV2.CartViewHolder holder, int position) {

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        item = itemsCart.get(position);

        String harga = itemsCart.get(position).getPrice();

        holder.name_cart.setText(itemsCart.get(position).getProductName());
        holder.desc_cart.setText("Color : "+itemsCart.get(position).getColor());
        holder.ukuran_cart.setText("Size : "+itemsCart.get(position).getSize());
        holder.qty_cart.setText("Qty : "+itemsCart.get(position).getQty());

        int totalPrice = ((Integer.valueOf(itemsCart.get(position).getPrice()))) * Integer.valueOf(itemsCart.get(position).getQty());
        overTotalPrice = totalPrice;
        holder.price.setText("Rp" + String.valueOf(totalPrice));
        holder.price.setText(formatRupiah.format((double)Integer.valueOf(harga)));

        Picasso.with(context).load(item.getProductImgHover()).into(holder.img_cart);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (onItemClickListener!=null)
//                    onItemClickListener.onItemClick(item.getId());
//                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemsCart == null)
            return 0;

        return itemsCart.size();
    }

    public void removeItem (int position){
        if (onItemClickListener!=null)
            onItemClickListener.onItemClick(item.getId());
        itemsCart.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem (DataItemCart item, int position){
        if (onItemClickListener!=null)
            onItemClickListener.onItemClick(item.getId());
        itemsCart.add(position,item);
        notifyItemInserted(position);
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_cart)
        ImageView img_cart;
        @BindView(R.id.name_cart)
        TextView name_cart;
        @BindView(R.id.desc_cart)
        TextView desc_cart;
        @BindView(R.id.ukuran_cart)
        TextView ukuran_cart;
        @BindView(R.id.qty_cart)
        TextView qty_cart;
        @BindView(R.id.price_cart)
        TextView price;
        @BindView(R.id.delete)
        ImageView delete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}

