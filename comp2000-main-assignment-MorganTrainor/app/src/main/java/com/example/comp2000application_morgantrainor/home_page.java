package com.example.comp2000application_morgantrainor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class home_page extends AppCompatActivity {

    private Button Settingsbutton;
    private Button Loginbutton;
    private Button admLoginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        // assigns the login fields as username and password
        EditText username = (EditText) findViewById(R.id.homepage_username);
        EditText password = (EditText) findViewById(R.id.homepage_password);

        // listens for a click and calls settings_page()
        Button bt_settings = (Button) findViewById(R.id.homepage_settingsbutton);
        bt_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings_page();
            }
        });

        // if the login details are admin/employee it will navigate to the respective navigation page
        Button bt_login = (Button) findViewById(R.id.homepage_loginbutton);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(home_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    NavPage();
                } else if (username.getText().toString().equals("employee") && password.getText().toString().equals("employee")) {
                    Toast.makeText(home_page.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    emp_nav_page();
                } else {
                    Toast.makeText(home_page.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    // Navigates to the settings page
    public void settings_page() {
        Intent intent = new Intent(this, Settings_Page.class);
        startActivity(intent);
    }
    // Navigates to the employee nav page
    public void emp_nav_page() {
        Intent intent = new Intent(this, emp_nav_page.class);
        startActivity(intent);
    }
    // Navigates to the admin nav page
    public void NavPage() {
        Intent intent = new Intent(this, NavPage.class);
        startActivity(intent);
    }
}