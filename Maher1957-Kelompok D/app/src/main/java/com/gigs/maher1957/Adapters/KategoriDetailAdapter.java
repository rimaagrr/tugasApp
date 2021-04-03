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
import com.gigs.maher1957.Fragments.OnFavClickListener;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.Models.KategoriDetail.ResultCategoryItem;
import com.gigs.maher1957.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class KategoriDetailAdapter extends RecyclerView.Adapter<KategoriDetailAdapter.KategoriDetailViewHolder> {

    private LayoutInflater inflater;
    private List<ResultCategoryItem> resultCategoryItem;
    private OnItemClickListener onItemClickListener;
    private OnFavClickListener onFavClickListener;
    Context context;

    public KategoriDetailAdapter(List<ResultCategoryItem> resultCategoryItem, Context context, OnItemClickListener onItemClickListener) {
        this.resultCategoryItem = resultCategoryItem;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.onFavClickListener = onFavClickListener;
    }

    @NonNull
    @Override
    public KategoriDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null)
            inflater = LayoutInflater.from(parent.getContext());
        return new KategoriDetailViewHolder(inflater.inflate(R.layout.item_grid, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriDetailViewHolder holder, int i) {
        final ResultCategoryItem item = resultCategoryItem.get(i);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        String harga = resultCategoryItem.get(i).getPrice();
        String harga2 = resultCategoryItem.get(i).getPriceResult();
        String diskon = resultCategoryItem.get(i).getDisc();

        holder.name.setText(resultCategoryItem.get(i).getName());
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
                    onItemClickListener.onItemClick(Integer.parseInt(item.getId2()));
            }
        });

        holder.btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(Integer.parseInt(item.getId2()));
            }
        });

//        holder.btn_addToFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onFavClickListener!=null)
//                    onFavClickListener.onFavClick(Integer.parseInt(resultCategoryItem.get(i).getId2()));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (resultCategoryItem == null)
            return 0;

        return resultCategoryItem.size();
    }

    public  class KategoriDetailViewHolder extends RecyclerView.ViewHolder {
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

        public KategoriDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}

