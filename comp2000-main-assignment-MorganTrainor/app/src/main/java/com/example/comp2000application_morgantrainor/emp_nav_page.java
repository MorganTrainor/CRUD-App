package com.example.comp2000application_morgantrainor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class emp_nav_page extends AppCompatActivity {

    private Button MyDetailsbutton;
    private Button RequestHolidaybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_nav);
        // listens for a click and calls mydetails()
        Button bt_mydetails = findViewById(R.id.empnav_mydetails);
        bt_mydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydetails();
            }
        });
        // listens for a click and calls reqholiday()
        Button bt_reqholiday = findViewById(R.id.empnav_requestholiday);
        bt_reqholiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqholiday();
            }
        });
        // listens for a click and calls navhomepage()
        Button bt_navhomepage = findViewById(R.id.empnav_homebutton);
        bt_navhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navhomepage();
            }
        });
    }

    // Navigates to the my details page
    public void mydetails() {
        Intent intent = new Intent(this, My_details.class);
        startActivity(intent);
    }

    // Navigates to the holiday requests page
    public void reqholiday() {
        Intent intent = new Intent(this, Request_Holiday.class);
        startActivity(intent);
    }

    // Navigates to the home page
    public void navhomepage() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}