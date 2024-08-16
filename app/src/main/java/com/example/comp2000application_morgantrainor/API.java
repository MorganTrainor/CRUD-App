package com.example.comp2000application_morgantrainor;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface API {
    // retrofit 2 API declarations

    // retrieves a list of Employee objects
    @GET("employees")
    Call<List<Employee>> employee_get();
    // creates a new Employee object
    @POST("employees")
    Call<Employee> employee_Post(@Body Employee employee);
    // deletes an Employee object
    @DELETE("employees/{id}")
    Call<Employee> employee_Delete(@Path("id") int delID);
    // upodates an employee object
    @PUT("employees/{id}")
    Call<Void> employee_Put(@Path ("id") int upID, @Body Employee employee);
}
