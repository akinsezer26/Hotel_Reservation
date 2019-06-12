package com.mobilproje.ogu.otelrezervasyon;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__info);


        TextView tvName=findViewById(R.id.Profil_ProfilInfo_isim);
        TextView tvSurName=findViewById(R.id.Profil_ProfilInfo_Soyisim);
        TextView tvEmail=findViewById(R.id.Profil_ProfilInfo_Email);
        TextView tvTel=findViewById(R.id.Profil_ProfilInfo_Tel);
        String isim,soyisim,email,tel;

        SharedPreferences info= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isim=info.getString("ConsumerName","");
        soyisim=info.getString("ConsumerSurname","");
        tel=info.getString("ConsumerPhone","");
        email=info.getString("ConsumerMail","");
        Long ConsumerID=info.getLong("ConsumerID",0);
        tvName.setText("İsim       :  "+isim);
        tvSurName.setText("Soyisim :  "+soyisim);
        tvTel.setText("Telefon  :  "+tel);
        tvEmail.setText("E-mail    :  "+email);

        DatabaseHandler dbh=new DatabaseHandler();
        Bitmap picture=dbh.GetConsumerProfilPicture(ConsumerID);
        ((ImageView) findViewById(R.id.profile_Info_image)).setImageBitmap(picture);
    }
}
