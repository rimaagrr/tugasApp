package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.gigs.maher1957.Models.Slide.DataSlide;
import com.gigs.maher1957.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SliderViewHolder>{

    private List<DataSlide> slideModels;
    private ViewPager2 viewPager2;
    private Context context;

    public SlideAdapter(List<DataSlide> slideModels, ViewPager2 viewPager2, Context context) {
        this.slideModels = slideModels;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_slide,
                        parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int i) {
        DataSlide dataSlide=slideModels.get(i);
        Picasso.with(context).load(dataSlide.getImgMobile()).into(holder.imageView);
        if (i == slideModels.size() - 2) {
            viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return slideModels.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemSlide)
        RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slideModels.addAll(slideModels);
            notifyDataSetChanged();
        }
    };
}