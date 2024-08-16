package com.example.comp2000application_morgantrainor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Employee_Details extends AppCompatActivity {
    private TextView apiResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_empdetails);
        //home button redirect to homepage
        Button bt_admEmpDetHomepage = (Button) findViewById(R.id.admempdetails_homebutton);
        bt_admEmpDetHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admEmpDet_Homepage();
            }
        });

        // creating a retrofit object with a base URL
        apiResults = findViewById(R.id.holReq_Placeholder6);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);

        // returns a list of Employee objects from the API
        Call<List<Employee>> call = api.employee_get();

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            // if the API responds successfully it iterates over the data, pushing it to the TextView
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    apiResults.setText("Code. " + response.code());
                    return;
                }
                // loops through the objects returned from the API and appends the information into the data string, which is then put into apiResults
                List<Employee> employees = response.body();
                for (Employee employee : employees) {
                    String data = "";
                    data += "ID: " + employee.getId() + " " + "Forename: " + employee.getForename() + " " + "Surname: " + employee.getSurname() + "\n\n";
                    apiResults.append(data);
                }
            }

            // if the network request fails it will get the error message
            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                apiResults.setText(t.getMessage());
            }
        });

        // show add popup
        ImageButton emp_add = (ImageButton) this.findViewById(R.id.empdet_add);
        emp_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empdet_popup_add();
            }
        });

        // show update popup
        ImageButton emp_update = (ImageButton) this.findViewById(R.id.empdet_update);
        emp_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employee_Update();
            }
        });

        //show delete popup
        ImageButton emp_delete = (ImageButton) this.findViewById(R.id.empdet_delete);
        emp_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empdet_popup_delete();
            }
        });


    }

    //  creating the popup window and assigning variables to the buttons
    public void empdet_popup_add() {
        final View getPopup = getLayoutInflater().inflate(R.layout.empdet_popup_add, null);
        Button addButton = (Button) getPopup.findViewById(R.id.empdat_popup_addbutton);
        Button cancelButton = (Button) getPopup.findViewById(R.id.empdat_popup_cancelbutton);
        AlertDialog.Builder createPopup;
        createPopup = new AlertDialog.Builder(this);
        createPopup.setView(getPopup);
        AlertDialog popup = createPopup.create();
        popup.show();
        // adds the data to the api
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText empdet_putForename = getPopup.findViewById(R.id.empdat_popup_forename);
                EditText empdet_putSurname = getPopup.findViewById(R.id.empdat_popup_surname);
                EditText empdet_putID = getPopup.findViewById(R.id.empdat_popup_ID);
                int putID = Integer.valueOf(empdet_putID.getText().toString());
                Employee employee = new Employee(putID, empdet_putForename.getText().toString(), empdet_putSurname.getText().toString());
                post_Employee(employee);
                popup.dismiss();
                refresh();
            }
        });
        // makes the cancel button dismiss the popup
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    // if the response code doesnt equal 400 it will post and if it isnt 400 it will fail, also if anything else breaks it fails
    private void post_Employee(Employee employee) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<Employee> post = api.employee_Post(employee);
        post.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.code() != 400) {
                    Toast.makeText(Employee_Details.this, "Employee Added", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Employee_Details.this, "Add Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(Employee_Details.this, "Add Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void empdet_popup_update() {

    }

    // creates the delete popup and assigns variables to the buttons
    public void empdet_popup_delete() {
        final View getPopup = getLayoutInflater().inflate(R.layout.empdet_popup_delete, null);
        Button deleteButton = (Button) getPopup.findViewById(R.id.empdat_delete_delbutton);
        Button cancelButton = (Button) getPopup.findViewById(R.id.empdat_delete_cancelbutton);
        AlertDialog.Builder createPopup;
        createPopup = new AlertDialog.Builder(this);
        createPopup.setView(getPopup);
        AlertDialog popup = createPopup.create();
        popup.show();
        // removes the data from the api
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText empdet_deleteID = getPopup.findViewById(R.id.empdat_delete_ID);
                int delID = Integer.valueOf(empdet_deleteID.getText().toString());
                employee_Delete(delID);

                popup.dismiss();
                refresh();
            }
        });
        // cancel button dismisses the popup
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    // checks to see if the API responds and makes a toast for it
    public void employee_Delete(int delID) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<Employee> post = api.employee_Delete(delID);
        post.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(Employee_Details.this, "Employee Deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(Employee_Details.this, "Delete Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //  creating the popup window and assigning variables to the buttons
    public void employee_Update() {
        final View getPopup = getLayoutInflater().inflate(R.layout.empdet_popup_update, null);
        Button addButton = (Button) getPopup.findViewById(R.id.empdat_update_updatebutton);
        Button cancelButton = (Button) getPopup.findViewById(R.id.empdat_update_cancelbutton);
        AlertDialog.Builder createPopup;
        createPopup = new AlertDialog.Builder(this);
        createPopup.setView(getPopup);
        AlertDialog popup = createPopup.create();
        popup.show();
        // adds the employee to the table, dismisses the popup and refreshes the page to show the changes
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText empdat_upForename = getPopup.findViewById(R.id.empdat_update_forename);
                EditText empdat_upSurname = getPopup.findViewById(R.id.empdat_update_surname);
                EditText empdat_upID = getPopup.findViewById(R.id.empdat_update_ID);
                int upID = Integer.valueOf(empdat_upID.getText().toString());
                Employee employee = new Employee(upID, empdat_upForename.getText().toString(), empdat_upSurname.getText().toString());

                upEmployee(upID, employee);
                popup.dismiss();
                refresh();
            }
        });
        // cancel button dismisses the popup
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    // checks to see if the API responds and makes a toast for it based on result
    public void upEmployee(int upID, Employee employee) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/").addConverterFactory(GsonConverterFactory.create()).build();
        API api = retrofit.create(API.class);
        Call<Void> post = api.employee_Put(upID, employee);
        post.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Employee_Details.this, "Employee Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Employee_Details.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Navigates to the home page
    public void admEmpDet_Homepage() {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }

    // refreshes the employee details page
    public void refresh() {
        Intent intent = new Intent(this, Employee_Details.class);
        startActivity(intent);
    }


}