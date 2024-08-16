package com.example.comp2000application_morgantrainor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class Settings_Page extends AppCompatActivity {
    private Switch themeSwitch;
    private Switch notifSwitch;
    private boolean notifEnabled;
    private Button buttonTestNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        // code to save the state of items and collect the state of the notifEnabled boolean
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        notifEnabled = prefs.getBoolean("notifEnabled", false);

        // creates a notification channel for Android Oreo (API 26) or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        getSupportActionBar().hide();


        // checks to see if the theme toggle has been clicked and if it is checked it activates night theme + toasts
        themeSwitch = findViewById(R.id.settings_themeswitch);
        themeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(getApplicationContext(), "Dark Mode", Toast.LENGTH_SHORT).show();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    Toast.makeText(getApplicationContext(), "Light Mode", Toast.LENGTH_SHORT).show();

                }
            }
        });
        // checks to see if the app is in night mode and changes themeSwitch based on that boolean
        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        themeSwitch.setChecked(isNightModeOn);

        // assigns the home page intent (at the bottom) to the button
        Button bt_homepage = findViewById(R.id.settings_homebutton);
        bt_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_page();
            }
        });
        // listens to the notifSwitch and changes the notifEnabled bool based on its state and saves it in a SharedPreferences interface + toasts
        notifSwitch = findViewById(R.id.settings_notificationswitch);
        notifSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifSwitch.isChecked()) {
                    notifEnabled = true;
                    Toast.makeText(getApplicationContext(), "Notifications Enabled", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("notifEnabled", notifEnabled);
                    editor.apply();
                } else {
                    notifEnabled = false;
                    Toast.makeText(getApplicationContext(), "Notifications Disabled", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("notifEnabled", notifEnabled);
                    editor.apply();
                }
            }
        });
        // sets the notifSwitch based on the result pulled from notifEnabled during onCreate
        notifSwitch.setChecked(notifEnabled);
        buttonTestNotification = findViewById(R.id.settings_testnotification);
        buttonTestNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifEnabled == true) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Settings_Page.this, "My Notification");
                    builder.setContentTitle("Test Notification");
                    builder.setContentText("This is a test notification");
                    builder.setSmallIcon(R.drawable.ic_baseline_person_outline_24);
                    builder.setAutoCancel(true);
                    builder.setDefaults(Notification.DEFAULT_SOUND);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Settings_Page.this);
                    managerCompat.notify(1, builder.build());
                    String notifEnabledString = String.valueOf(notifEnabled);
                    Toast.makeText(getApplicationContext(), "Notifications are " + notifEnabledString, Toast.LENGTH_SHORT).show();
                } else {

                    String notifEnabledString = String.valueOf(notifEnabled);
                    Toast.makeText(getApplicationContext(), "Notifications are " + notifEnabledString, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // gives a smooth transition to the theme switch
    public void recreate() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    // creates and intent to go to the home page and starts it
    public void home_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }


}

