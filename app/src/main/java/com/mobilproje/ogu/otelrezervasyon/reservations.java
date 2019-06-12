package com.mobilproje.ogu.otelrezervasyon;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class reservations extends AppCompatActivity {

    ArrayList<Reservation> reservations;
    ListView rezList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        rezList=findViewById(R.id.RezList);
        try {
            getRez();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rezList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    public void getRez() throws SQLException {

        SharedPreferences info= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Long consumerID=info.getLong("ConsumerID",0);
        DatabaseHandler databaseHandler=new DatabaseHandler();
        ResultSet resultSet=databaseHandler.GetReservations(consumerID);
        reservations=new ArrayList<>();
        while (resultSet.next()) {
            Reservation reservation=new Reservation();
            reservation.setConsumerID(resultSet.getLong("ConsumerID"));
            reservation.setReservationID(resultSet.getLong("ReservationID"));
            reservation.setRoomID(resultSet.getLong("RoomID"));
            reservation.setReservationPrice(resultSet.getInt("ReservationPrice"));
            reservation.setReservationStartDate(resultSet.getDate("ReservationStartDate"));
            reservation.setReservationEndDate(resultSet.getDate("ReservationEndDate"));
            reservations.add(reservation);
        }

        ReservationAdapter reservationAdapter=new ReservationAdapter(this,reservations);
        rezList.setAdapter(reservationAdapter);
    }
}
