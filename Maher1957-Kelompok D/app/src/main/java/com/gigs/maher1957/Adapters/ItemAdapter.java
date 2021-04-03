package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.MainActivity;
import com.gigs.maher1957.Fragments.HomeFragment;
import com.gigs.maher1957.Fragments.OnFavClickListener;
import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private LayoutInflater inflater;
    private List<DataItem> items;
    private OnFavClickListener onFavClickListener;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public ItemAdapter(List<DataItem> items, Context context , OnItemClickListener onItemClickListener) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
        this.onFavClickListener = onFavClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if (inflater==null)
            inflater =LayoutInflater.from(parent.getContext());
        return new ItemHolder(inflater.inflate(R.layout.item_grid, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int i) {

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        final DataItem item = items.get(i);

        String harga = items.get(i).getPrice();
        String harga2 = items.get(i).getPriceResult();
        String diskon = items.get(i).getDisc();

        holder.name.setText(items.get(i).getName());
        holder.price.setText(formatRupiah.format((double)Integer.valueOf(harga)));
        holder.price2.setText(formatRupiah.format((double)Integer.valueOf(harga2)));
        holder.disc.setText(diskon+"%");
        Glide.with(context).load(item.getImgHover()).into(holder.image);

        if (diskon != null ){
            holder.price.setPaintFlags(holder.price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            holder.price.setTextSize(15);

        } else {
            holder.price2.setVisibility(View.GONE);
            holder.price.setTypeface(null, Typeface.BOLD);
            holder.price.setTextSize(16);
            holder.price.setTextColor(Color.parseColor("#000000"));
            holder.disc.setVisibility(View.GONE);
        }

        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(item.getId());
            }
        });
//        holder.btn_addToFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onFavClickListener!=null)
//                    onFavClickListener.onFavClick(item.getId());
//            }
//        });
        holder.btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(item.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }
    public void filterList(List<DataItem> filtered) {
        this.items = filtered;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_grid)
        ImageView image;
        @BindView(R.id.name_grid)
        TextView name;
        @BindView(R.id.price_grid)
        TextView price;
        @BindView(R.id.price2_grid)
        TextView price2;
        @BindView(R.id.disc_grid)
        TextView disc;
        @BindView(R.id.btn_addToCartGrid)
        Button btn_addToCart;
//        @BindView(R.id.btn_addFavGrid)
//        ImageView btn_addToFavorite;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}