package com.mobilproje.ogu.otelrezervasyon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelInfo extends AppCompatActivity {

    Long HotelID;
    TextView hotelInfo,hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_hotel_info);


        hotelInfo=findViewById(R.id.hotel_info_txtDesc);
        hotelName=findViewById(R.id.hotel_info_hotelname);
        Bundle extra = getIntent().getExtras();
        HotelID=extra.getLong("HotelID");
        ImageView img=findViewById(R.id.Hotel_info_image);

        DatabaseHandler dbh=new DatabaseHandler();
        hotelInfo.setText(dbh.getHotelDescr(HotelID).toString());
        hotelName.setText(extra.getString("HotelName"));
        img.setImageBitmap(dbh.GetHotelProfilPicture(HotelID));
    }


    public void rezervasyon(View view) {
        Intent i = new Intent(HotelInfo.this,DoReservation.class);
        i.putExtra("HotelID",HotelID);
        startActivity(i);
    }


}
