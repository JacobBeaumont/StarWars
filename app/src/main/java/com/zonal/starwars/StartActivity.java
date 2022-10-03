package com.zonal.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.zonal.starwars.view.StarWarsActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAnimation("spaceride.json");
        Button buttonGetPlanets = findViewById(R.id.btnGetplanets);
        buttonGetPlanets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, StarWarsActivity.class);
                startActivity(intent);
                //finish();
            }
        });

    }


    public void setAnimation(String animation) {
        LottieAnimationView lottieAnimationView = findViewById(R.id.animationView);
        lottieAnimationView.setAnimation(animation);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.setScaleType(ImageView.ScaleType.FIT_XY);
        lottieAnimationView.playAnimation();
    }
}
