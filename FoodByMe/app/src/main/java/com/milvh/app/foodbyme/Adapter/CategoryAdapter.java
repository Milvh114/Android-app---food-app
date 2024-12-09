package com.milvh.app.foodbyme.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.milvh.app.foodbyme.Activity.ListFoodsActivity;
import com.milvh.app.foodbyme.Domain.Category;
import com.milvh.app.foodbyme.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {

        holder.titleTxt.setText(items.get(position).getName());

        switch (position){
            case 0:
                holder.pic.setBackgroundResource(R.drawable.background_cat_0);
                break;
            case 1:
                holder.pic.setBackgroundResource(R.drawable.background_cat_1);
                break;
            case 2:
                holder.pic.setBackgroundResource(R.drawable.background_cat_2);
                break;
            case 3:
                holder.pic.setBackgroundResource(R.drawable.background_cat_3);
                break;
            case 4:
                holder.pic.setBackgroundResource(R.drawable.background_cat_4);
                break;
            case 5:
                holder.pic.setBackgroundResource(R.drawable.background_cat_5);
                break;
            case 6:
                holder.pic.setBackgroundResource(R.drawable.background_cat_6);
                break;
            case 7:
                holder.pic.setBackgroundResource(R.drawable.background_cat_7);
                break;
        }
        int drawableResourceId= context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable", holder.itemView.getContext().getPackageName());
        System.out.println("drawableResourceId"+drawableResourceId);
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodsActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}
