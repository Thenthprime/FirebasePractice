package edu.psu.swen888.firebasepractice;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    //show the splash screen for 3 seconds
    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //get the nittany lion
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.psu_nittany);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //go from splash screen to main activity
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}

