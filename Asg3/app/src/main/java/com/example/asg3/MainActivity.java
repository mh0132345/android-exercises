package com.example.asg3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText lastName, firstName, phoneNumber, address, email, birthday;
    String tag = "Screen orientation is changed at: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        lastName = findViewById(R.id.lastName);
        firstName = findViewById(R.id.firstName);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthday);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String dateTime = s.format(new Date());
        outState.putString("dateTime",dateTime);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String dateTime = savedInstanceState.getString("dateTime");
        Log.d(tag, tag + dateTime);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String dateTime = s.format(new Date());
        Log.d(tag, tag + dateTime);
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
