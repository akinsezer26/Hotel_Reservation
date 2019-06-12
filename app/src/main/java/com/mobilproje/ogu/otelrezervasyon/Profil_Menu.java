package com.mobilproje.ogu.otelrezervasyon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Profil_Menu extends AppCompatActivity {

    ImageButton profilImageButton;
    Long consumerID;
    private static final int IMAGE_ACTION_CODE = 102;

    Bitmap Picture;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) return;

        switch (requestCode) {

            case IMAGE_ACTION_CODE :
                Bundle extras = data.getExtras();
                Picture=(Bitmap)extras.get("data");
                ((ImageView) findViewById(R.id.profile_image)).setImageBitmap((Bitmap) extras.get("data"));
                break;
            default:
                break;
        }
    }

    EditText etName,etSurName,etEmail,etTel,etSifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil__menu);
        etName=findViewById(R.id.Profil_Profil_isim);
        etSurName=findViewById(R.id.Profil_Profil_Soyisim);
        etEmail=findViewById(R.id.Profil_Profil_Email);
        etTel=findViewById(R.id.Profil_Profil_Tel);
        etSifre=findViewById(R.id.Profil_Profil_sifre);
        profilImageButton=findViewById(R.id.Profil_img_but);
        SharedPreferences info= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String isim=info.getString("ConsumerName","");
        String soyisim=info.getString("ConsumerSurname","");
        String tel=info.getString("ConsumerPhone","");
        String email=info.getString("ConsumerMail","");
        String sifre=info.getString("ConsumerPassword","");
        consumerID=info.getLong("ConsumerID",0);
        etName.setText(isim);
        etSurName.setText(soyisim);
        etTel.setText(tel);
        etEmail.setText(email);
        etSifre.setText(sifre);

        DatabaseHandler dbh=new DatabaseHandler();
        Picture=dbh.GetConsumerProfilPicture(consumerID);
        ((ImageView) findViewById(R.id.profile_image)).setImageBitmap(Picture);

        profilImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, IMAGE_ACTION_CODE);
            }
        });
    }

    public void Guncelle(View view) {
        DatabaseHandler databaseHandler=new DatabaseHandler();
        databaseHandler.ProfilePhotoAdd(consumerID,Picture);

        Consumer consumer=new Consumer();

        consumer.setConsumerID(consumerID);
        consumer.setConsumerName(etName.getText().toString());
        consumer.setConsumerSurname(etSurName.getText().toString());
        consumer.setConsumerPhone(etTel.getText().toString());
        consumer.setConsumerMail(etEmail.getText().toString());
        consumer.setConsumerPassword(etSifre.getText().toString());

        databaseHandler.UpdateConsumer(consumer);

        SharedPreferences islogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=islogin.edit();
        editor.putString("ConsumerName",etName.getText().toString());
        editor.putString("ConsumerSurname",etSurName.getText().toString());
        editor.putString("ConsumerPhone",etTel.getText().toString());
        editor.putString("ConsumerMail",etEmail.getText().toString());
        editor.putString("ConsumerPassword",etSifre.getText().toString());
        editor.commit();

        Intent startActivity = new Intent(Profil_Menu.this, NavigationMenu.class);
        startActivity(startActivity);
        finish();
    }
}
