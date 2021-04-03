package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Fragments.OnKategoriClickListener;
import com.gigs.maher1957.Models.Kategori.DataKategori;
import com.gigs.maher1957.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriHolder> {

    private LayoutInflater inflater;
    private List<DataKategori> dataKategoris;
    private OnKategoriClickListener onItemClickListener;
    private Context context;

    public KategoriAdapter(List<DataKategori> dataKategoris, Context context , OnKategoriClickListener onItemClickListener) {
        this.dataKategoris = dataKategoris;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public KategoriHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if (inflater==null)
            inflater =LayoutInflater.from(parent.getContext());
        return new KategoriHolder(inflater.inflate(R.layout.item_kategori, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriHolder holder, int i) {
        final DataKategori item = dataKategoris.get(i);

        holder.name.setText(dataKategoris.get(i).getName());
        Picasso.with(context).load(item.getImgCateProduct()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onKategoriClick(dataKategoris.get(i).getSlug());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dataKategoris == null)
            return 0;

        return dataKategoris.size();
    }
    public static class KategoriHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_kategori)
        ImageView image;
        @BindView(R.id.nama_kategori)
        TextView name;

        public KategoriHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
