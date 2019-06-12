package com.mobilproje.ogu.otelrezervasyon;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class HotelRowAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private List<Hotel> mHotelListesi;

    public HotelRowAdapter(Activity activity, List<Hotel> hotels) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mHotelListesi = hotels;
    }



    @Override
    public int getCount() {
        return mHotelListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotelListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.hotelrow, null);
        TextView hotelName = satirView.findViewById(R.id.HRHotelName);
        TextView hotelInfo = satirView.findViewById(R.id.HRHotelInfo);
        ImageView hotelPicture = satirView.findViewById(R.id.HRHotelPicture);
        Hotel hotel = mHotelListesi.get(position);
        hotelName.setText(hotel.getHotelName());
        hotelInfo.setText(hotel.getHotelIntroduction());
        hotelPicture.setImageBitmap(hotel.getProfilePicture());
        DatabaseHandler dbh=new DatabaseHandler();
        Bitmap picture=dbh.GetHotelProfilPicture(hotel.getHotelID());
        hotelPicture.setImageBitmap(picture);
        return satirView;
    }
}
