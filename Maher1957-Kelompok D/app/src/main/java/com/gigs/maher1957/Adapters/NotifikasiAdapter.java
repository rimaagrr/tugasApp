package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Fragments.OnListOrderClickListener;
import com.gigs.maher1957.Models.Notifikasi.OrdersItem;
import com.gigs.maher1957.Fragments.OnItemClickListener;
import com.gigs.maher1957.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotifikasiAdapter extends RecyclerView.Adapter<com.gigs.maher1957.Adapters.NotifikasiAdapter.NotifikasiViewHolder> {

    private List<OrdersItem> orderList;
    private Context context;
    private OnListOrderClickListener onListOrderClickListener;

    public NotifikasiAdapter(List<OrdersItem> orderList, Context context,OnListOrderClickListener onListOrderClickListener) {
        this.orderList = orderList;
        this.context = context;
        this.onListOrderClickListener = onListOrderClickListener;
    }

    @NonNull
    @Override
    public com.gigs.maher1957.Adapters.NotifikasiAdapter.NotifikasiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notifikasi, viewGroup, false);
        return new com.gigs.maher1957.Adapters.NotifikasiAdapter.NotifikasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.gigs.maher1957.Adapters.NotifikasiAdapter.NotifikasiViewHolder holder, int position) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        formatRupiah.setMinimumFractionDigits(0);
        formatRupiah.setMaximumFractionDigits(0);

        final OrdersItem item = orderList.get(position);

        Object notes = orderList.get(position).getNote();

        holder.tittle.setText(orderList.get(position).getPaymentStatus());
        holder.invoiceID.setText(": "+orderList.get(position).getCode());
        holder.status.setText(": "+orderList.get(position).getStatus());
        holder.grandPrice.setText(": "+formatRupiah.format((double)Integer.parseInt(orderList.get(position).getGrandTotal())));
        holder.time.setText(orderList.get(position).getOrderDate());
        if(notes != null){
            holder.note.setText("Note : "+orderList.get(position).getNote());
        }else {
            holder.note.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListOrderClickListener != null)
                    onListOrderClickListener.onListOrderClick(item.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class NotifikasiViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.notif_tittle)
        TextView tittle;
        @BindView(R.id.notif1)
        TextView invoiceID;
        @BindView(R.id.notif2)
        TextView status;
        @BindView(R.id.notif3)
        TextView grandPrice;
        @BindView(R.id.notif4)
        TextView note;
        @BindView(R.id.notif_time)
        TextView time;


        public NotifikasiViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}

