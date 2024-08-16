package com.example.comp2000application_morgantrainor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class Request_Holiday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emp_requestholiday);
        // listens for a click and calls home_page()
        Button bt_homepage = (Button) findViewById(R.id.requestholiday_homebutton);
        bt_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_page();
            }
        });
    }
    // Navigates to the home page
    public void home_page() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }
}