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

import com.gigs.maher1957.Models.Cart.ItemsInCartItem;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<ItemsInCartItem> itemsCart;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private int overTotalPrice = 0;

    public CartAdapter(List<ItemsInCartItem> itemsCart, Context context, OnItemClickListener onItemClickListener) {
        this.itemsCart = itemsCart;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cart,viewGroup,false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        final ItemsInCartItem item = itemsCart.get(position);
        holder.name_cart.setText(itemsCart.get(position).getProductName());
        holder.desc_cart.setText("Color : "+itemsCart.get(position).getColor());
        holder.ukuran_cart.setText("Size : "+itemsCart.get(position).getSize());
        holder.qty_cart.setText(" x "+itemsCart.get(position).getQty());
//        holder.weight.setText("Berat : "+itemsCart.get(position).getWeight());
//        holder.price.setText("Rp"+itemsCart.get(position).getPrice());

        int totalPrice = ((Integer.valueOf(itemsCart.get(position).getPrice()))) * Integer.valueOf(itemsCart.get(position).getQty());
        overTotalPrice = totalPrice;
        holder.price.setText("Rp" + String.valueOf(totalPrice));

        Picasso.with(context).load(item.getProductImgHover()).into(holder.img_cart);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (itemsCart == null)
            return 0;

        return itemsCart.size();
    }

//    public int grandPrice(){
//        int totalPrice = 0;
//        for (int i = 0; i < itemsCart.size(); i++){
//            totalPrice += itemsCart.get(i).getProductId();
//        }
//        return totalPrice;
//    }

    public void removeItem (int position){
        itemsCart.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem (ItemsInCartItem item, int position){
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
//        @BindView(R.id.weight_cart)
//        TextView weight;
        @BindView(R.id.price_cart)
        TextView price;
//        @BindView(R.id.btnCheckbox)
//        CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
