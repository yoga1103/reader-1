package com.example.shaany.reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button prayers;
    Button calendar;
    Button about;
    Button candles;
    Button gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prayers = (Button) findViewById(R.id.prayers);
        calendar = (Button) findViewById(R.id.calendar);
        about = (Button) findViewById(R.id.about);
        candles = (Button) findViewById(R.id.candles);
        gallery= (Button) findViewById(R.id.gallery);
        prayers.setOnClickListener(prayerHandler);
        calendar.setOnClickListener(calendarHandler);
        candles.setOnClickListener(candleHandler);
        about.setOnClickListener(aboutHandler);
        gallery.setOnClickListener(galleryHandler);
    }

        public void loadPage(int id, String div) {
            Intent intent = new Intent(getApplicationContext(),Post.class);
            intent.putExtra("id", ""+id);
            intent.putExtra("div", ""+div);
            startActivity(intent);
        }

        View.OnClickListener prayerHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(177, "prayers");
            }
        };

        View.OnClickListener calendarHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(96, "cal");
            }
        };

        View.OnClickListener candleHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(161, "candles");
            }
        };

        View.OnClickListener aboutHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(276, "cat");
            }
        };

        View.OnClickListener galleryHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(280, "cat");
            }
        };
}