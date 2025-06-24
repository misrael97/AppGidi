package com.example.appgidi.models;

public class LoginResponse {
    private String status;
    private String msg;
    private LoginData data;

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public LoginData getData() {
        return data;
    }

    public class LoginData {
        private int id;
        private String email;
        private String token;

        public int getId() { return id; }
        public String getEmail() { return email; }
        public String getToken() { return token; }
    }
}
