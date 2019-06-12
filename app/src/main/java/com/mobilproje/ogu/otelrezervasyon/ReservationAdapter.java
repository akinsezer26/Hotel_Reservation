package com.mobilproje.ogu.otelrezervasyon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReservationAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Reservation> mRezListesi;
    public ReservationAdapter(Activity activity, List<Reservation> reservations) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mRezListesi = reservations;
    }

    @Override
    public int getCount() {
        return mRezListesi.size();
    }

    @Override
    public Object getItem(int position) {
        return mRezListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.reservationrow, null);
        TextView hotelName = satirView.findViewById(R.id.rHotelName);
        TextView rStartDate = satirView.findViewById(R.id.rStartDate);
        TextView rEndDate = satirView.findViewById(R.id.rEndDate);
        Reservation reservation=mRezListesi.get(position);
        DatabaseHandler databaseHandler=new DatabaseHandler();
        hotelName.setText("Hotel Adı:"+databaseHandler.getHotelName(reservation.getRoomID()));
        rStartDate.setText("Giriş Tarihi:"+String.valueOf(reservation.getReservationStartDate()));
        rEndDate.setText("Çıkış Tarihi:"+String.valueOf(reservation.getReservationEndDate()));
        return satirView;
    }
}
