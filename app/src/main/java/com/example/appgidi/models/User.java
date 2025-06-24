package com.example.appgidi.models;

public class User {
    private String email;
    private String password;
    private String client;

    public User(String email, String password, String client) {
        this.email = email;
        this.password = password;
        this.client = client;
    }

    // Getters (si usas Gson, setters no son necesarios)
}