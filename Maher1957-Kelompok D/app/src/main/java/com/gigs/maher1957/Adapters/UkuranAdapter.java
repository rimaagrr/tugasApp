package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.gigs.maher1957.Models.Product.ProductSizesItem;
import com.gigs.maher1957.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UkuranAdapter extends RecyclerView.Adapter<UkuranAdapter.SliderViewHolder> {

    private List<ProductSizesItem> ukuranModels;
    private Context context;
    private int lastSelectedPosition = -1;
    private RadioButton lastChecked = null;

    public UkuranAdapter(List<ProductSizesItem> ukuranModels, Context context) {
        this.ukuranModels = ukuranModels;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_ukuran,
                        parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int i) {

        ProductSizesItem slideModel = ukuranModels.get(i);
        holder.btnUkuran.setText(slideModel.getName());
//        holder.btnRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
//                if(btnUkuran != null){
//                    btnUkuran.setChecked(false);
//                }
//                btnUkuran = radioButton;
//            }
//        });
        holder.btnUkuran.setChecked(slideModel.isChecked());
        holder.btnUkuran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (ProductSizesItem sizesItem : ukuranModels){
                    sizesItem.setChecked(false);
                }
                ukuranModels.get(i).setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ukuranModels.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        public RadioButton btnUkuran;
        public RadioGroup btnRadio;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUkuran = (RadioButton) itemView.findViewById(R.id.btnUkuran);
            btnRadio = (RadioGroup) itemView.findViewById(R.id.btnRadio);

//            btnUkuran.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    lastSelectedPosition = getAdapterPosition();
//                    notifyDataSetChanged();
//                }
//            });
//            btnRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup group, int checkedId) {
//                    RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
//                if(lastChecked != null){
//                    lastChecked.setChecked(false);
//                }
//                lastChecked = radioButton;
//            }
//            });
        }
    }
}
