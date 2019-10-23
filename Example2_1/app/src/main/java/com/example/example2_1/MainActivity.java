package com.example.example2_1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String current_time = dateFormat.format(date);
    String tag = "Example Demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, current_time + "onCreate()");
    }
    protected void onStart() {
        super.onStart();
        Log.d(tag, current_time + "onStart()");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(tag, current_time + "onReStart()");
    }
    protected void onResume() {
        super.onResume();
        Log.d(tag, current_time + "onResume()");
    }
    protected void onPause() {
        super.onPause();
        Log.d(tag, current_time + "onPause()");
    }
    protected void onStop() {
        super.onStop();

        Log.d(tag, current_time + "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, current_time + "onDestroy()");
    }
}