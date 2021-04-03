package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Models.Checkout.DtCartitems;
import com.gigs.maher1957.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.PesananViewHolder> {

    private List<DtCartitems> dataListPesanan;
    private Context context;
    private int overTotalPrice = 0;

    public PesanAdapter(List<DtCartitems> dataListPesanan, Context context) {
        this.dataListPesanan = dataListPesanan;
        this.context = context;
    }

    @NonNull
    @Override
    public PesanAdapter.PesananViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_belanjaan,viewGroup,false);
        return new PesanAdapter.PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesanAdapter.PesananViewHolder holder, int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        String harga = dataListPesanan.get(position).getPrice();

        holder.name.setText(dataListPesanan.get(position).getProductName());
        holder.qty.setText(" x "+dataListPesanan.get(position).getQty());

        int totalPrice = ((Integer.valueOf(dataListPesanan.get(position).getPrice()))) * Integer.valueOf(dataListPesanan.get(position).getQty());
        overTotalPrice = totalPrice;
        holder.price.setText("Rp" + String.valueOf(totalPrice));
        holder.price.setText(formatRupiah.format((double)Integer.valueOf(harga)));

    }

    @Override
    public int getItemCount() {
        return (dataListPesanan != null) ? dataListPesanan.size() : 0;
    }

    public class PesananViewHolder extends RecyclerView.ViewHolder {

        private TextView name, price, qty;

        public PesananViewHolder(@NonNull View itemView) {
            super(itemView);

            qty = (TextView) itemView.findViewById(R.id.qty_pesanan);
            name = (TextView) itemView.findViewById(R.id.name_pesanan);
            price = (TextView) itemView.findViewById(R.id.harga_pesanan);;
        }
    }
}
