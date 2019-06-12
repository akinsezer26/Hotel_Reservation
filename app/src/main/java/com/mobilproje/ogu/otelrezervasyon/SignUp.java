package com.mobilproje.ogu.otelrezervasyon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText Name;
    EditText Surname;
    EditText Email;
    EditText Phone;
    EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name =findViewById(R.id.SU_Isim);
        Surname=findViewById(R.id.SU_Soyisim);
        Email=findViewById(R.id.SU_Email);
        Phone=findViewById(R.id.SU_Telefon);
        Password=findViewById(R.id.SU_Sifre);
    }

    public void KayitOl(View view) {
        if (Name.getText().toString().equals("") || Surname.getText().toString().equals("") || Email.getText().toString().equals("")
                || Password.getText().toString().equals("") || Phone.getText().toString().equals("")) {
            Toast.makeText(this, "Lütfen tüm bilgileri doldurunuz", Toast.LENGTH_SHORT).show();
        } else {
            final Consumer consumer = new Consumer();
            consumer.setConsumerName(Name.getText().toString());
            consumer.setConsumerSurname(Surname.getText().toString());
            consumer.setConsumerMail(Email.getText().toString());
            consumer.setConsumerPhone(Phone.getText().toString());
            consumer.setConsumerPassword(Password.getText().toString());
            DatabaseHandler databaseHandler = new DatabaseHandler();
            if (databaseHandler.DatabaseSignUp(consumer)) {
                Toast.makeText(this, "Kayıt Başarılı Girişiniz Yapılıyor", Toast.LENGTH_SHORT).show();
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

                        Intent startActivity = new Intent(SignUp.this, NavigationMenu.class);
                        startActivity(startActivity);
                        finish();

                    }
                }, 1200);

            } else {
                Toast.makeText(this, "Girilen E-mail Adresi Kullanılıyor", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
