package com.example.aldrinlim.watchyourresttime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textTimer;
    private Timer timer = new Timer();
    private Button stop;
    private int sec = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTimer = findViewById(R.id.timer);
        stop = findViewById(R.id.stopbutton);


        // stop timer from running
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Timer Stopped", Toast.LENGTH_LONG).show();
                timer.cancel();
            }
        });


        // call start timer function
        startTimer();



    }


    void startTimer () {

        // create a timer thread for running time
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                // use ui thread to change text while time is running
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       sec++;
                       textTimer.setText(formatSecToTimer(sec));
                   }
               });
            }
        }, 1000, 1000);
    }


    // format seconds to mm:ss
    String formatSecToTimer(int sec) {
        Date d = new Date(sec * 1000L);
        SimpleDateFormat df = new SimpleDateFormat("mm:ss"); // HH for 0-23
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = df.format(d);
        return time;
    }
}
