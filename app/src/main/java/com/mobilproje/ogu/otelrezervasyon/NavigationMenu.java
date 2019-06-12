package com.mobilproje.ogu.otelrezervasyon;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NavigationMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText etHotelSearchName;
    Button btnBul;
    ListView hotelList;
    ArrayList<Hotel> hotels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etHotelSearchName=findViewById(R.id.citySearchName);
        hotelList=findViewById(R.id.HotelListe);
        findViewById(R.id.btnSearchHotel);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        hotelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NavigationMenu.this,HotelInfo.class);
                i.putExtra("HotelID",hotels.get(position).getHotelID());
                i.putExtra("HotelName",hotels.get(position).getHotelName());
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            Intent i = new Intent(this,Profil_Info.class);
            startActivity(i);
            //finish();
        } else if (id == R.id.nav_reservations) {
            Intent i = new Intent(this,reservations.class);
            startActivity(i);

        }else if (id == R.id.nav_ConfigProfil) {
            Intent i = new Intent(this,Profil_Menu.class);
            startActivity(i);
            //finish();
        } else if (id == R.id.nav_CreditCard) {


        }
        else if(id==R.id.nav_logout){
            SharedPreferences islogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=islogin.edit();
            editor.putBoolean("isLogin",false);
            editor.commit();

            Intent i = new Intent(this,Login.class);
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void SearchHotel(View view) throws SQLException {
       String girilenler[]=null;
       String girilen= etHotelSearchName.getText().toString();
       girilenler=girilen.split(",");
       DatabaseHandler databaseHandler=new DatabaseHandler();
       ResultSet resultSet=databaseHandler.SearchHotel(girilenler[0],girilenler[1]);
       hotels=new ArrayList<>();
       while (resultSet.next()) {
           Hotel hotel=new Hotel();
           hotel.setHotelID(resultSet.getLong("HotelID"));
           hotel.setHotelName(resultSet.getString("HotelName"));
           hotel.setHotelIntroduction(resultSet.getString("HotelIntroduction"));
           hotel.setProfilePicture(databaseHandler.GetHotelProfilPicture(hotel.getHotelID()));
           hotels.add(hotel);
       }

        HotelRowAdapter hotelRowAdapter=new HotelRowAdapter(this,hotels);
        hotelList.setAdapter(hotelRowAdapter);
    }
}
