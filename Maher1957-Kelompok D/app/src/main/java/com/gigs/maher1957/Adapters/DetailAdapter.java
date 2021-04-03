package com.gigs.maher1957.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.Models.Product.ProductImagesItem;
import com.gigs.maher1957.Models.Slide.DataSlide;
import com.gigs.maher1957.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    private List<ProductImagesItem> productImagesItems;
    private Context context;

    public DetailAdapter( Context context, List<ProductImagesItem> productImagesItems) {
        this.productImagesItems = productImagesItems;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_slide,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        ProductImagesItem item = productImagesItems.get(position);
        Picasso.with(context).load(item.getProductImgName()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productImagesItems.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemSlide)
        RoundedImageView imageView;

        DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}