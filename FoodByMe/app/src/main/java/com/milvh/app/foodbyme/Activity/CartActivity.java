package com.milvh.app.foodbyme.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.milvh.app.foodbyme.Adapter.CartAdapter;
import com.milvh.app.foodbyme.Helper.ManagmentCart;
import com.milvh.app.foodbyme.R;
import com.milvh.app.foodbyme.databinding.ActivityCartBinding;
import com.milvh.app.foodbyme.databinding.ActivityLoginBinding;

public class CartActivity extends BaseActivity {

    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;

    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setVariable();
        caculateCart();
        initListCart();
    }

    private void setVariable() {
        binding.backCartBtn.setOnClickListener(v ->{
            System.out.println("chay di ");
            finish();
        });
    }

    private void initListCart() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyCartTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }else {
            binding.emptyCartTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, () -> caculateCart());
        binding.cardView.setAdapter(adapter);
    }

    private void caculateCart() {
        double percentTax = 0.05;
        double delivery= 10;

        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100)/100;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) *100)/100;
        double itemTotal= Math.round(managmentCart.getTotalFee()*100)/100;

        binding.totalFeeWithoutTaxTxt.setText("$"+itemTotal);
        binding.taxFeeTxt.setText("$"+ tax);
        binding.deliveryFeeTxt.setText("$"+delivery);
        binding.totalFeeTxt.setText("$"+total);
    }


}