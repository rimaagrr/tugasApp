package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.gigs.maher1957.Activity.PesanActivity;
import com.gigs.maher1957.Models.ListModel;
import com.gigs.maher1957.R;

import java.util.List;

public class KuponAdapter extends RecyclerView.Adapter<com.gigs.maher1957.Adapters.KuponAdapter.KuponViewHolder> {

    private List<ListModel> listModelList;
    private Context context;

    public KuponAdapter(List<ListModel> listModelList, Context context) {
        this.listModelList = listModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public com.gigs.maher1957.Adapters.KuponAdapter.KuponViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kupon, viewGroup, false);
        return new com.gigs.maher1957.Adapters.KuponAdapter.KuponViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.gigs.maher1957.Adapters.KuponAdapter.KuponViewHolder holder, int position) {

        ListModel listModel = listModelList.get(position);

        holder.title.setText(listModel.getTitle());
        holder.desc.setText(listModel.getDesc());
        holder.time.setText(listModel.getTime());
        Glide.with(context).load(listModel.getImage()).placeholder(R.drawable.g2).into(holder.image);

//        holder.btnAktif.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Kupon Berhasil Diaktifkan", Toast.LENGTH_SHORT).show();
//                Intent kupon = new Intent(context, PesanActivity.class);
//                context.startActivity(kupon);
//                Animatoo.animateZoom(context);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listModelList.size();
    }

    public class KuponViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title, desc, time;
        private Button btnAktif;


        public KuponViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            time = (TextView) itemView.findViewById(R.id.time);
//            btnAktif = (Button) itemView.findViewById(R.id.btnAktif);
        }
    }
}
