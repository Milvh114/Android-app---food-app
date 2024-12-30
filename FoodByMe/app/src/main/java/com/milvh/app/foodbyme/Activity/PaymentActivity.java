package com.milvh.app.foodbyme.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.milvh.app.foodbyme.R;
import com.milvh.app.foodbyme.databinding.ActivityIntro2Binding;
import com.milvh.app.foodbyme.databinding.ActivityPaymentBinding;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v ->{
            finish();
        });

        binding.finishBtn.setOnClickListener( v -> {
            Toast.makeText(PaymentActivity.this, "payment successful", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(PaymentActivity.this, MainActivity.class);
            startActivity(i);
        });

        binding.copyBtn.setOnClickListener(v -> {
            String textToCopy = binding.stkView.getText().toString();

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
            clipboard.setPrimaryClip(clip);

            // Hiển thị thông báo
            Toast.makeText(this, "Đã sao chép: " + textToCopy, Toast.LENGTH_SHORT).show();
        });
    }
}