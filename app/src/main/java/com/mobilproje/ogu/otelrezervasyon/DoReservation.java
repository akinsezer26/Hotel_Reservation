package com.mobilproje.ogu.otelrezervasyon;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.LongBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DoReservation extends AppCompatActivity {

    Long HotelID;
    ImageView hotelRoomPicture;
    TextView hotelName;
    Spinner roomType;
    TextView price;
    TextView roomIntro;
    DatePicker startDate;
    DatePicker endDate;
    Button doReser;
    Long roomID;
    int roomPrice;
    ArrayList<HotelAndRoom> hotelAndRooms;
    ArrayList<String> roomTypes;
    int sDay=1,sMonth=1,sYear=2018;
    int eDay=1,eMonth=1,eYear=2018;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_reservation);
        hotelRoomPicture=findViewById(R.id.HIhotelroompictures);
        hotelName=findViewById(R.id.HIhotelname);
        roomType=findViewById(R.id.HIroomtype);
        price=findViewById(R.id.HIroomprice);
        roomIntro=findViewById(R.id.HIroomintroduction);
        startDate=findViewById(R.id.HIstartdate);
        endDate=findViewById(R.id.HIEnddate);
        doReser=findViewById(R.id.HIrezervasyonYap);
        Bundle extra = getIntent().getExtras();
        HotelID=extra.getLong("HotelID");
        hotelAndRooms=new ArrayList<>();
        roomTypes=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
         sYear    = calendar.get(Calendar.YEAR);
         sMonth   = calendar.get(Calendar.MONTH)+1;
         sDay     = calendar.get(Calendar.DAY_OF_MONTH);
         eYear    = calendar.get(Calendar.YEAR);
         eMonth   = calendar.get(Calendar.MONTH)+1;
         eDay     = calendar.get(Calendar.DAY_OF_MONTH);
        try {
            getInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayA=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,roomTypes);
        roomType.setAdapter(arrayA);
        hotelName.setText(hotelAndRooms.get(0).getHotelName());
        roomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 price.setText(String.valueOf(hotelAndRooms.get(position).getRoomPrice())+" Türk Lirası");
                 roomIntro.setText(hotelAndRooms.get(position).getRoomIntroduction().toString());
                 roomID=hotelAndRooms.get(position).getRoomID();
                 roomPrice=hotelAndRooms.get(position).getRoomPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        doReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler databaseHandler=new DatabaseHandler();
               if( databaseHandler.ControlReservation(sDay,sMonth,sYear,eDay,eMonth,eYear,roomID))
               {
                   SharedPreferences info= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                   Long consumerID=info.getLong("ConsumerID",0);
                   int resPrice=((Math.abs(eDay-sDay))+(Math.abs(eMonth-sMonth))*30+(Math.abs(eYear-sYear))*365)*roomPrice;
                   if(databaseHandler.CreateReservation(resPrice,consumerID,roomID,sDay,sMonth,sYear,eDay,eMonth,eYear))
                   {
                       Toast.makeText(DoReservation.this, "Rezervasyonunuz başarı bir şekilde alınmıştır.", Toast.LENGTH_SHORT).show();
                   }
               }
               else
               {
                   Toast.makeText(DoReservation.this, "Tarih aralığında boş oda bulunmamaktadır.", Toast.LENGTH_SHORT).show();
               }
            }
        });
        Calendar c = Calendar.getInstance();
        startDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int newYear, int newMonth, int newDay) {
                      sDay=newDay;
                      sMonth=newMonth+1;
                      sYear=newYear;
                }
        });
        endDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int newYear, int newMonth, int newDay) {
                        eDay=newDay;
                        eMonth=newMonth+1;
                        eYear=newYear;
                }
        });
    }


    public void getInfo() throws SQLException {
        DatabaseHandler databaseHandler=new DatabaseHandler();
        ResultSet resultSet=databaseHandler.GetHotelAndRoom(HotelID);
        while (resultSet.next()) {
            HotelAndRoom hotelAndRoom=new HotelAndRoom();
            hotelAndRoom.setHotelID(resultSet.getLong("HotelID"));
            hotelAndRoom.setHotelName(resultSet.getString("HotelName"));
            hotelAndRoom.setHotelIntroduction(resultSet.getString("HotelIntroduction"));
            hotelAndRoom.setHotelDistrict(resultSet.getString("District"));
            hotelAndRoom.setHotelProvince(resultSet.getString("Province"));
            hotelAndRoom.setRoomID(resultSet.getLong("RoomID"));
            hotelAndRoom.setRoomType(resultSet.getString("RoomType"));
            roomTypes.add(resultSet.getString("RoomType").toString());
            hotelAndRoom.setRoomPrice(resultSet.getInt("RoomPrice"));
            hotelAndRoom.setRoomIntroduction(resultSet.getString("RoomIntroduction"));
            hotelAndRoom.setRoomCount(resultSet.getInt("RoomCount"));
            hotelAndRooms.add(hotelAndRoom);
        }
    }
}
