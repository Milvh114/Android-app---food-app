package com.milvh.app.foodbyme.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.milvh.app.foodbyme.Domain.Food;
import com.milvh.app.foodbyme.Helper.ManagmentCart;
import com.milvh.app.foodbyme.R;
import com.milvh.app.foodbyme.databinding.ActivityDetailBinding;
import com.milvh.app.foodbyme.databinding.ActivityMainBinding;

public class DetailActivity extends BaseActivity {

    ActivityDetailBinding binding;

    private int num = 1;
    private Food object;

    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        hideSystemUI();

        getIntentExtra();
        setVariable();

    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    private void setVariable() {
        managmentCart= new ManagmentCart(this);
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.foodImg);

        binding.priceTxt.setText("$"+object.getPrice());
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar()+ " Rating");
        binding.totalTxt.setText(((num * object.getPrice()) + "$"));

        binding.plusBtn.setOnClickListener(v -> {
            num=num+1;
            binding.numTxt.setText(num+" ");
            binding.totalTxt.setText("$ "+(num * object.getPrice()));
        });

        binding.minusBtn.setOnClickListener(v -> {
            if (num>1){
                num=num-1;
                binding.numTxt.setText(num+" ");
                binding.totalTxt.setText("$ "+(num * object.getPrice()));
            }
        });

        binding.addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });
    }

    private void getIntentExtra() {

         object = (Food) getIntent().getSerializableExtra("object");
    }
}