package com.mobilproje.ogu.otelrezervasyon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class Login extends AppCompatActivity {

    TextView tvHesapOlustur;
    EditText loginEmail,loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvHesapOlustur=findViewById(R.id.TVHesapOlustur);
        loginEmail=findViewById(R.id.Login_Email);
        loginPassword=findViewById(R.id.LoginSifre);

        boolean isLoged;
        SharedPreferences isLogin=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isLoged=isLogin.getBoolean("isLogin",false);

        if(isLoged){
            Intent startActivity = new Intent(Login.this, NavigationMenu.class);
            startActivity(startActivity);
            finish();
        }

        tvHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startActivity = new Intent(Login.this, SignUp.class);
                startActivity(startActivity);
            }
        });
    }

    public void GirisYap(View view) throws SQLException {
        DatabaseHandler databaseHandler=new DatabaseHandler();
        ResultSet resultSet=databaseHandler.LoginTo(loginEmail.getText().toString());
        final Consumer consumer=new Consumer();
        if (resultSet.next())
        {
            consumer.setConsumerID(resultSet.getLong("ConsumerID"));
            consumer.setConsumerName(resultSet.getString("ConsumerName"));
            consumer.setConsumerSurname(resultSet.getString("ConsumerSurname"));
            consumer.setConsumerPassword(resultSet.getString("ConsumerPassword"));
            consumer.setConsumerMail(resultSet.getString("ConsumerMail"));
            consumer.setConsumerPhone(resultSet.getString("ConsumerPhone"));
        }

        if(loginPassword.getText().toString().equals(consumer.getConsumerPassword()))
        {
            Toast.makeText(this, "Girişiniz Yapılıyor", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences islogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor=islogin.edit();
                    editor.putBoolean("isLogin",true);
                    editor.putLong("ConsumerID",consumer.getConsumerID());
                    editor.putString("ConsumerName",consumer.getConsumerName());
                    editor.putString("ConsumerSurname",consumer.getConsumerSurname());
                    editor.putString("ConsumerPassword",consumer.getConsumerPassword());
                    editor.putString("ConsumerPhone",consumer.getConsumerPhone());
                    editor.putString("ConsumerMail",consumer.getConsumerMail());
                    editor.commit();

                    Intent startActivity = new Intent(Login.this, NavigationMenu.class);
                    startActivity.putExtra("ConsumerName",consumer.getConsumerName());
                    startActivity.putExtra("ConsumerSurname",consumer.getConsumerSurname());
                    startActivity.putExtra("ConsumerID",consumer.getConsumerID());
                    startActivity.putExtra("ConsumerPhone",consumer.getConsumerPhone());
                    startActivity.putExtra("ConsumerMail",consumer.getConsumerMail());
                    startActivity.putExtra("ConsumerPassword",consumer.getConsumerPassword());
                    startActivity(startActivity);
                    finish();

                }
            }, 1200);
        }
        else
        {
            Toast.makeText(this, "Hatalı Bilgi Girdiniz.", Toast.LENGTH_SHORT).show();
        }
    }
}
