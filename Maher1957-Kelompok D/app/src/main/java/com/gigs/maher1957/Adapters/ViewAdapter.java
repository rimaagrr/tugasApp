package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gigs.maher1957.Models.Product.DataItem;
import com.gigs.maher1957.Models.Product.ProductImagesItem;
import com.gigs.maher1957.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private StringBuilder sb;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<DataItem> dataItemList;
    private List<ProductImagesItem> productImagesItems;
    private List<String> img;
    public ViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        dataItemList = new ArrayList<>();
        img = new ArrayList<>();
        for (int i = 0; i < dataItemList.size(); i++){
            img.add(dataItemList.get(i).getProductImages().get(i).getProductImgName());
        }
        sb = new StringBuilder();
        for (String s : img)
        {
            sb.append(s);
            sb.append("\t");
        }
        return img.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.itemdetail,null);
        ImageView imageView = view.findViewById(R.id.imageDetail);
        imageView.setImageResource(Integer.parseInt(sb.toString()));
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);


    }
}
