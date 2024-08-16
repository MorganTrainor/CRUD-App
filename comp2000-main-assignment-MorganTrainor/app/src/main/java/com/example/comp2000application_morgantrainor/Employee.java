package com.example.comp2000application_morgantrainor;

public class Employee {
    private int id;
    private String surname;
    private String forename;
    // creates the constructor for the Employee class
    public Employee(int id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }
    // returns getID as id
    public int getId() {
        return id;
    }
    // sets the id variable to the id parameter
    public void setId(int id) {
        this.id = id;
    }
    // returns getSurname as surname
    public String getSurname() {
        return surname;
    }
    // returns getForename as forename
    public String getForename() {
        return forename;
    }
}
