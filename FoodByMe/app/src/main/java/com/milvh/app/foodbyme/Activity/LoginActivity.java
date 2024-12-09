package com.milvh.app.foodbyme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.milvh.app.foodbyme.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(v -> {
            String email = binding.userEdit.getText().toString();
            String pass = binding.passEdit.getText().toString();

            if(!email.isEmpty() && !pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()){
                        Intent i = new Intent(LoginActivity.this, IntroActivity2.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                Toast.makeText(LoginActivity.this, "please fill username and password", Toast.LENGTH_SHORT).show();
            }
        });
        binding.signupBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}