package com.gigs.maher1957.Adapters;

import android.content.Context;
import android.graphics.Paint;
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
import com.gigs.maher1957.Models.Favorite.DataGetFav;
import com.gigs.maher1957.Models.Favorite.Product;
import com.gigs.maher1957.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private LayoutInflater inflater;
    private List<Product> favList;
    private List<DataGetFav> dataGetFavList;
    private OnItemClickListener onItemClickListener;
    private OnFavClickListener onFavClickListener;
    Context context;

    public FavoriteAdapter(List<Product> favList,List<DataGetFav> dataGetFavList, Context context, OnItemClickListener onItemClickListener,OnFavClickListener onFavClickListener) {
        this.favList = favList;
        this.dataGetFavList = dataGetFavList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.onFavClickListener = onFavClickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null)
            inflater = LayoutInflater.from(parent.getContext());
        return new FavoriteViewHolder(inflater.inflate(R.layout.item_fav, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int i) {
        final Product item = favList.get(i);
        final DataGetFav dataGetFav = dataGetFavList.get(i);
        holder.name.setText(favList.get(i).getName());
        holder.price.setText("Rp"+favList.get(i).getPrice());
        holder.price.setPaintFlags(holder.price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.price2.setText("Rp"+favList.get(i).getPriceResult());
        holder.disc.setText(favList.get(i).getDisc()+"%");
        Glide.with(context).load(item.getImgHover()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(item.getId());
            }
        });
//        holder.btn_addToFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!favList.get(i).isAddedTocart()){
//                    favList.get(i).setAddedTocart(true);
//                    holder.btn_addToFavorite.setImageResource(R.drawable.ic_favorit_hitam);
//                    remove(item);
////                    Snackbar.make(v, toString() + ", Dihapus", Snackbar.LENGTH_LONG)
////                            .setAction("Batalkan", new View.OnClickListener() {
////                                @Override
////                                public void onClick(View v) {
////                                    favList.remove(item);
////                                    favList.add(position, item);
////                                    holder.btn_addToFavorite.setImageResource(R.drawable.ic_heart_merah);
////                                    notifyItemInserted(position);
////                                }
////                            }).show();
//                    Toast.makeText(context, "dihapus", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            public void remove(Product item) {
//                favList.remove(item);
//                notifyDataSetChanged();
//            }
//        });
        holder.btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(item.getId());
            }
        });

        holder.btn_addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFavClickListener!=null)
                    onFavClickListener.onFavClick(dataGetFav.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (favList == null)
            return 0;

        return favList.size();
    }

    public  class FavoriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_gridFav)
        ImageView image;
        @BindView(R.id.name_gridFav)
        TextView name;
        @BindView(R.id.price_gridFav)
        TextView price;
        @BindView(R.id.price2_gridFav)
        TextView price2;
        @BindView(R.id.disc_gridFav)
        TextView disc;
        @BindView(R.id.btn_addToCartGridFavorite)
        Button btn_addToCart;
        @BindView(R.id.btn_addToFavoriteFav)
        ImageView btn_addToFavorite;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
