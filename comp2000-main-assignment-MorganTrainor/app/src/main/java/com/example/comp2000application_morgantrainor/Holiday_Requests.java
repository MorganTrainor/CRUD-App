package com.example.comp2000application_morgantrainor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Holiday_Requests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_holidayrequests);
        // listens for a click and calls holreq_homepage()
        Button bt_holreq_homepage = (Button) findViewById(R.id.holreq_homebutton);
        bt_holreq_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holreq_homepage();}
        });
    }
    // Navigates to the home page
    public void holreq_homepage() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}