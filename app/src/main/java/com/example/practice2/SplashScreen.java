package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    private ImageView logoImage;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        setContentView(R.layout.activity_splash_screen);




      int[] logo = {R.drawable.logo1 , R.drawable.logo2 , R.drawable.logo3 , R.drawable.logo4};

        logoImage = (ImageView) findViewById(R.id.logoimage);
        logoImage.setImageResource(logo[generateRandomLogo()]);

        Log.d("TaG", String.valueOf(generateRandomLogo()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if (sharedPreferences.getBoolean("logout", false) == true)
                {
                    i = new Intent(SplashScreen.this , MainActivity.class);
                }
                else {

                    i = new Intent(SplashScreen.this,

                            SignupActivity.class);
                }

                startActivity(i);


                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
    public  int generateRandomLogo() {
        int range = (3 - 0) + 1;
        return (int)(Math.random() * range) + 0;
    }

}