package com.example.comp2000application_morgantrainor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_nav);
        // listens for a click and calls admnav_homepage()
        Button bt_admnavhomepage = (Button) findViewById(R.id.admnav_homebutton);
        bt_admnavhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admnav_homepage();}
        });
        // Navigates to the home page
        Button bt_empdetails = (Button) findViewById(R.id.empdetails_button);
        bt_empdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admnav_empdetails();}
        });
        // listens for a click and calls admnav_reqhol()
        Button bt_admReqHolhomepage = (Button) findViewById(R.id.holreq_button);
        bt_admReqHolhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admnav_reqhol();}
        });
    }
    // Navigates to the home page
    public void admnav_homepage() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
    // Navigates to the employee details page
    public void admnav_empdetails() {
        Intent intent = new Intent(this, Employee_Details.class);
        startActivity(intent);
    }
    // Navigates to the holiday requests page
    public void admnav_reqhol() {
        Intent intent = new Intent(this, Holiday_Requests.class);
        startActivity(intent);
    }




}