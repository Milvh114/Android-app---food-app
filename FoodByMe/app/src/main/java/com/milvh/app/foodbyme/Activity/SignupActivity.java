package com.milvh.app.foodbyme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.milvh.app.foodbyme.R;
import com.milvh.app.foodbyme.databinding.ActivitySignupBinding;

public class SignupActivity extends BaseActivity {

    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.userEdt.getText().toString();
                String pass=binding.passEdt.getText().toString();

                if(pass.length() < 6){
                    Toast.makeText(SignupActivity.this, "your password must be 6 char", Toast.LENGTH_SHORT).show();
                    return;
                }
//                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isComplete()){
//                            Log.i(TAG, "onComplete: ");
//                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
//                            startActivity(i);
//                        }else {
//                            Log.i(TAG, "failure: "+task.getException());
//                            Toast.makeText(SignupActivity.this, "Authentication", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(SignupActivity.this, task -> {
                    if(task.isComplete()){
                        Log.i(TAG, "onComplete: ");
                        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(i);
                    }else {
                        Log.i(TAG, "failure: "+task.getException());
                        Toast.makeText(SignupActivity.this, "Authentication", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.loginBtn.setOnClickListener(v -> {
            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(i);
        });
    }
}