package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigs.maher1957.Models.Alamat.Provinsi.DataProvinsi;
import com.gigs.maher1957.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ProvinsiHolder> {

    private LayoutInflater inflater;
    private List<DataProvinsi> dataProvinsiList;
    private Context context;

    public ProvinsiAdapter(List<DataProvinsi> dataProvinsiList, Context context ) {
        this.dataProvinsiList = dataProvinsiList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProvinsiHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        if (inflater==null)
            inflater =LayoutInflater.from(parent.getContext());
        return new ProvinsiHolder(inflater.inflate(R.layout.activity_alamat, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinsiHolder holder, int i) {

        final DataProvinsi dataProvinsi = dataProvinsiList.get(i);
        holder.name.setText(dataProvinsiList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        if (dataProvinsiList == null)
            return 0;

        return dataProvinsiList.size();
    }

    public class ProvinsiHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.provinsi)
        TextView name;

        public ProvinsiHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
