package com.mobilproje.ogu.otelrezervasyon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseHandler {
    Connection connection=null;

    public boolean DatabaseSignUp(Consumer consumer)
    {

        ResultSet resultSet = null;
        Connection con=this.ConnectToAzure();
        String control="select*from Consumer where ConsumerMail='"+consumer.getConsumerMail()+"';";
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(control);
        }
        catch (Exception e){

        }
        try {
            if (!resultSet.next()) {

                String query = "insert into Consumer(ConsumerName,ConsumerSurname,ConsumerPassword,ConsumerPhone,ConsumerMail) "
                        + "values('" + consumer.getConsumerName() + "','"
                        + consumer.getConsumerSurname() + "','"
                        + consumer.getConsumerPassword() + "','"
                        + consumer.getConsumerPhone() + "','"
                        + consumer.getConsumerMail() + "');";
                try {
                    Statement sta = con.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    sta.executeUpdate(query);
                    con.close();
                    connection.close();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            else
                return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public ResultSet LoginTo(String email){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;
        String query = "select * from Consumer where ConsumerMail='"+email+"'";
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
          resultSet =  sta.executeQuery(query);
          return resultSet;
        }

        catch (Exception e){
        return resultSet;
        }
    }

    public ResultSet SearchHotel(String province,String district){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;
        
        String query = "select * from Hotel where Province='"+province+"' and District='"+district+"'";
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(query);
            return resultSet;
        }

        catch (Exception e){
            return resultSet;
        }
    }

    public ResultSet GetReservations(Long consumerID){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;

        String query = "select * from Reservation where ConsumerID="+consumerID;
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(query);
            return resultSet;
        }

        catch (Exception e){
            return resultSet;
        }
    }
    public ResultSet GetHotelAndRoom(Long hotelID){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;

        String query = "select Hotel.HotelID,HotelName,HotelIntroduction,District,Province,Room.RoomID,RoomType,RoomPrice,RoomIntroduction,RoomCount from Hotel,HotelRoom,Room where Hotel.HotelID=HotelRoom.HotelID and Room.RoomID=HotelRoom.RoomID  and Hotel.HotelID="+hotelID;
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(query);
            return resultSet;
        }

        catch (Exception e){
            return resultSet;
        }
    }

    public String getHotelDescr(long hotelID){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;

        String query = "select HotelIntroduction from Hotel where HotelID="+hotelID;
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(query);
            resultSet.next();
            return resultSet.getString(1);
        }

        catch (Exception e){
            return "";
        }

    }

    public String getHotelName(long roomID){
        Connection con=this.ConnectToAzure();
        ResultSet resultSet = null;

        String query = "select Hotel.HotelName from Reservation,Room,HotelRoom,Hotel where Reservation.RoomID=Room.RoomID and Room.RoomID=HotelRoom.RoomID and HotelRoom.HotelID=Hotel.HotelID and Room.RoomID="+roomID;
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(query);
            resultSet.next();
            return resultSet.getString("HotelName");
        }

        catch (Exception e){
            return "";
        }

    }

    public boolean ProfilePhotoAdd(Long consumerID, Bitmap photo)
    {
        String controlquery = "select * from Picture where ConsumerID="+consumerID+";";
        Connection con=this.ConnectToAzure();
        ResultSet resultSet=null;
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet =  sta.executeQuery(controlquery);
        }catch (Exception e){
        }

        byte[] bArray = this.getBytesFromBitmap(photo);
        try {
            if (!resultSet.next()) {
                PreparedStatement preparedStatement;
                preparedStatement = con.prepareStatement("insert into Picture(ConsumerID,PictureData) values(?,?)");
                preparedStatement.setLong(1, consumerID);
                preparedStatement.setBytes(2, bArray);
                preparedStatement.executeUpdate();
                return true;
            }

            else{
                PreparedStatement preparedStatement;
                preparedStatement = con.prepareStatement("update Picture set PictureData=? where ConsumerID="+consumerID);
                preparedStatement.setBytes(1,bArray);
                preparedStatement.executeUpdate();
                return true;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    public Bitmap GetConsumerProfilPicture(Long ConsumerID){
        Connection con=this.ConnectToAzure();
        String query = "select PictureData from Picture where ConsumerID="+ConsumerID;

        Bitmap ProfilPic;
        ResultSet resultSet=null;
        try {
                    Statement sta = con.createStatement();
                    resultSet=sta.executeQuery(query);
                    byte[] byteArray=null;
                    while (resultSet.next()) {
                            byteArray=resultSet.getBytes(1);
                     }
            ProfilPic=(Bitmap) BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return ProfilPic;
        }

        catch (Exception e){
            return null;
        }
    }

    public boolean ControlReservation(int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear, Long roomId)
    {
        Connection con=this.ConnectToAzure();
        String query1 = "select count(ReservationID) from Reservation where (ReservationStartDate between '"+sYear+"-"+sMonth+"-"+sDay+"' and '"+eYear+"-"+eMonth+"-"+eDay+"' or ReservationEndDate between '"+sYear+"-"+sMonth+"-"+eDay+"' and '2018-05-21') and RoomID="+roomId;
        ResultSet resultSet1=null;
        String query2 = "select RoomCount from HotelRoom where RoomID="+roomId;
        ResultSet resultSet2=null;
        int fullRoom = 0;
        int allRoom = 0;
        try {
            Statement sta1 = con.createStatement();
            resultSet1=sta1.executeQuery(query1);
            Statement sta2 = con.createStatement();
            resultSet2=sta2.executeQuery(query2);
            if (resultSet1.next()) {
                fullRoom=resultSet1.getInt(1);
            }
            if (resultSet2.next()) {
                allRoom=resultSet2.getInt("RoomCount");
            }
            if(fullRoom<allRoom)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        catch (Exception e){
            return false;
        }
    }

    public boolean CreateReservation(int reservationPrice, Long consumerID, Long roomID, int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear)
    {
        String query = "insert into Reservation(ReservationPrice,ReservationStartDate,ReservationEndDate,ConsumerID,RoomID) values("+reservationPrice+",'"+sYear+"-"+sMonth+"-"+sDay+"','"+eYear+"-"+eMonth+"-"+eDay+"',"+consumerID+","+roomID+")";
        Connection con=this.ConnectToAzure();
        try {
            Statement sta = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        sta.executeUpdate(query);
        return true;
        }catch (Exception e){
            return false;
        }
    }
    public Bitmap GetHotelProfilPicture(Long HotelID){
        Connection con=this.ConnectToAzure();
        String query = "select PictureData from Picture where HotelID="+HotelID;

        Bitmap ProfilPic;
        ResultSet resultSet=null;
        try {
            Statement sta = con.createStatement();
            resultSet=sta.executeQuery(query);
            byte[] byteArray=null;
            while (resultSet.next()) {
                byteArray=resultSet.getBytes(1);
            }
            ProfilPic=(Bitmap) BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return ProfilPic;
        }

        catch (Exception e){
            return null;
        }
    }

    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    public Connection ConnectToAzure(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String ConnectionUrl="jdbc:jtds:sqlserver://heryerdenotel.database.windows.net:1433;DatabaseName=heryerdenoteldb;" +
                    "user=akinsezer26@heryerdenotel;password=Akin123456789;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            connection = DriverManager.getConnection(ConnectionUrl);
        }

        catch (android.database.SQLException se){
            Log.e("error here 1 : ",se.getMessage());
        }
        catch (ClassNotFoundException ce){
            Log.e("error here 1 : ",ce.getMessage());
        }
        catch (Exception e){
            Log.e("error here 1 : ",e.getMessage());
        }
        return connection;
    }

    public boolean UpdateConsumer(Consumer consumer){
        Connection con=this.ConnectToAzure();

        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement("update Consumer set ConsumerName=?,ConsumerSurname=?,ConsumerPhone=?,ConsumerMail=?,ConsumerPassword=? where ConsumerID=" + consumer.getConsumerID());
            preparedStatement.setString(1,consumer.getConsumerName());
            preparedStatement.setString(2,consumer.getConsumerSurname());
            preparedStatement.setString(3,consumer.getConsumerPhone());
            preparedStatement.setString(4,consumer.getConsumerMail());
            preparedStatement.setString(5,consumer.getConsumerPassword());
            preparedStatement.executeUpdate();
        }
        catch (Exception e){
            return false;
        }
        return  true;
    }
}
