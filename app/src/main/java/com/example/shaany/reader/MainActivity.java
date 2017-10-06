package com.example.shaany.reader;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.shaany.reader.R.id.announcements;


public class MainActivity extends AppCompatActivity {

    ImageButton prayers;
    ImageButton calendar;
    ImageButton about;
    ImageButton candles;
    ImageButton gallery;
    ImageButton Twitter;
    ImageButton Facebook;
    ImageButton Instagram;
    ImageButton Notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prayers = (ImageButton) findViewById(R.id.prayer);
        calendar = (ImageButton) findViewById(R.id.calendar);
        about = (ImageButton) findViewById(R.id.about);
        candles = (ImageButton) findViewById(R.id.candles);
        gallery = (ImageButton) findViewById(R.id.gallery);
        Twitter = (ImageButton) findViewById(R.id.Twitter);
        Facebook = (ImageButton) findViewById(R.id.Facebook);
        Instagram = (ImageButton) findViewById(R.id.Instagram);
        Notifications = (ImageButton) findViewById(R.id.announcements);
        prayers.setOnClickListener(prayerHandler);
        calendar.setOnClickListener(calendarHandler);
        candles.setOnClickListener(candleHandler);
        about.setOnClickListener(aboutHandler);
        gallery.setOnClickListener(galleryHandler);
        Twitter.setOnClickListener(TwitterHandler);
        Facebook.setOnClickListener(FacebookHandler);
        Instagram.setOnClickListener(InstagramHandler);
        Notifications.setOnClickListener(notificationHandler);
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
                loadPage(276, "aboutnf");
            }
        };

        View.OnClickListener notificationHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(390, "announce");
            }
        };

        View.OnClickListener galleryHandler = new View.OnClickListener() {
            public void onClick(View v) {
                loadPage(280, "gallery");
            }
        };

    //if the twitter button is pressed
    View.OnClickListener TwitterHandler = new View.OnClickListener() {

        public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.twitter.android");//check if twitter is installed
                if (intent != null) {
                    //if it is
                    // start it
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=768116050479251457"));//so apparently user id's  are numbers and not @user_id
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//make sure it does'nt open in this app
                    startActivity(intent);
                    //if twitter is not installed
                } else {
                    //open it in the browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/BahaiNorthFL"));
                    startActivity(intent);
                }
            }
    };

    View.OnClickListener FacebookHandler = new View.OnClickListener() {

        public void onClick(View v) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.facebook.android");//check if facebook is installed
            if (intent != null) {
                //if it is installed
                //start it
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/bahainorthflorida"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//make sure it does'nt open in this app
                startActivity(intent);
                //if facebook is'nt installed
            } else {
                //open it in the browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/bahainorthflorida/"));
                startActivity(intent);
            }
        }
    };

    View.OnClickListener InstagramHandler = new View.OnClickListener() {

        public void onClick(View v) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");//check if instagram is installed
            if (intent != null) {
                //if it is
                //start it
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/bahainorthflorida"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//make sure it does'nt open in this app
                startActivity(intent);
            } else {
                //open it in the browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/bahainorthflorida/"));
                startActivity(intent);
            }
        }
    };

}