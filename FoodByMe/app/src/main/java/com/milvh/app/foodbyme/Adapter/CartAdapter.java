package com.milvh.app.foodbyme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.milvh.app.foodbyme.Domain.Category;
import com.milvh.app.foodbyme.Domain.Food;
import com.milvh.app.foodbyme.Helper.ChangeNumberItemsListener;
import com.milvh.app.foodbyme.Helper.ManagmentCart;
import com.milvh.app.foodbyme.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {

    ArrayList<Food> listFood;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;
    Context context;

    public CartAdapter(ArrayList<Food> listFood, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listFood = listFood;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cart, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.title.setText(listFood.get(position).getTitle().toString());
        holder.feeOfEachItem.setText("$" + listFood.get(position).getPrice());
        holder.totalFeeOfItems.setText("$" + (listFood.get(position).getNumberInCart() * listFood.get(position).getPrice()));
        holder.quantityItem.setText(listFood.get(position).getNumberInCart()+ " ");

        Glide.with(holder.itemView.getContext())
                .load(listFood.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.foodImg);

        holder.plusItem.setOnClickListener( v -> {
            managmentCart.plusNumberItem(listFood, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });
        });

        holder.minusItem.setOnClickListener( v -> {
            managmentCart.minusNumberItem(listFood, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });
        });

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView title, feeOfEachItem, totalFeeOfItems, quantityItem, plusItem, minusItem;
        ImageView foodImg;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            feeOfEachItem = itemView.findViewById(R.id.feeOfItemTxt);
            totalFeeOfItems = itemView.findViewById(R.id.totalFeeOfItemsTxt);
            quantityItem = itemView.findViewById(R.id.quantityItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);

            foodImg = itemView.findViewById(R.id.foodImg);
        }
    }
}
