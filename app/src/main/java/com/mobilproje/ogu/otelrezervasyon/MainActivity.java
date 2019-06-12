package com.mobilproje.ogu.otelrezervasyon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView Baslangic_Resimleri;
    SharedPreferences preferences;
    boolean isLogin=false;
    Connection con;

    int[] resimler={R.drawable.temp1,R.drawable.temp2,R.drawable.temp3,R.drawable.temp4,R.drawable.temp5,R.drawable.temp6, R.drawable.temp7
    ,R.drawable.temp8,R.drawable.temp9,R.drawable.temp10};

    boolean isSuccess=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        int random = rand.nextInt(10);

        Baslangic_Resimleri =findViewById(R.id.BasResimleri);

        Baslangic_Resimleri.setImageResource(resimler[random]);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        isLogin=preferences.getBoolean("isLogin",false);

        Handler mHandler=new Handler();
        mHandler.postDelayed(mLaunchTask,3000);
    }


    Runnable mLaunchTask = new Runnable() {
        public void run() {
            if(!isLogin){
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
            else{
                Intent i = new Intent(MainActivity.this,NavigationMenu.class);
                startActivity(i);
                finish();
            }
        }
    };

}
