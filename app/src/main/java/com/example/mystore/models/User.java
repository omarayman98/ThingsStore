package com.example.mystore.models;

public class User {
    String name,Email,Password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        Email = email;
        Password = password;
    }
}
