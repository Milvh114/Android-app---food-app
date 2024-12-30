package com.milvh.app.foodbyme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;

import com.milvh.app.foodbyme.R;
import com.milvh.app.foodbyme.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {

    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

        hideSystemUI();
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
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() != null){
                    Intent i = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        VideoView videoView = binding.videoView;
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid_logo_black);

        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> videoView.start());
    }
}