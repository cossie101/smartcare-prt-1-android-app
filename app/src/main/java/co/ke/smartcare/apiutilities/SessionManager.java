package co.ke.smartcare.apiutilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.ke.smartcare.HomePageActivity;
import co.ke.smartcare.LoginActivity;

public class SessionManager {

    private final Context _context;
    SharedPreferences settings, user;

    SharedPreferences.Editor editor;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "smartCareData";

    private static  final String LOGGED_IN = "isLoggedIn";

    private static final String KEY_NAME = "username";

    private static final String TIME = "time";

    private static final int TIME_OUT = -1; //In hours

    private SimpleDateFormat sdf;

    //initialize session manager instance
    public SessionManager(Context context){
        this._context = context;
        settings = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = settings.edit();
    }

    //save logged in  user username and userId
    public void  patientDetails(int userId, String name, String username, String phone, String email,
                                String idNo, String account){
        editor.putBoolean(LOGGED_IN, true);
        editor.putInt("userId", userId);
        editor.putString("name", name);
        editor.putString("username", username);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.putString("idNo", idNo);
        editor.putString("account", account);
        editor.putString(TIME, this.getDateToday());
        editor.commit();
    }

    //get todays date as a string yyyy-mm-dd H:i:s
    public String getDateToday(){
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return year+"-"+month+"-"+dayOfMonth+" "+hour+":"+minute+":"+second;
    }

    //check for session timeout
    public void checkLogin(){
        if(!this.isLoggedIn()){
//            Intent i = new Intent(_context, MainActivity.class);
//
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            _context.startActivity(i);
        }else{
            Toast.makeText(_context, "Session Resume", Toast.LENGTH_SHORT).show();



            Intent intent = new Intent(_context, HomePageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(intent);
        }
    }

    //do logout
    public void logoutUser(){
        editor.clear();
        editor.commit();


        Intent i = new Intent(_context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }


    //check if session is expired
    public void isExpired(){
        //this.logoutUser();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String loginTime = settings.getString("time", "");

        String todaysDate = this.getDateToday();

        Date loginDate = sdf.parse(loginTime, new ParsePosition(0));

        Date dateToday = sdf.parse(todaysDate, new ParsePosition(0));

        if(loginDate != null){


            long diff = dateToday.getTime() -loginDate.getTime();

            long milliseconds = 1000;
            long minutesinMilli = milliseconds * 60;
            long hoursinMilli = minutesinMilli*60;

            long elapsedHours = diff/hoursinMilli;

            if(elapsedHours > TIME_OUT){
                Toast.makeText(_context, "Session expired", Toast.LENGTH_SHORT).show();
                this.logoutUser();
            }
        }


    }


    //boolean for login status
    public boolean isLoggedIn(){
        return settings.getBoolean("isLoggedIn", false);
    }
}
