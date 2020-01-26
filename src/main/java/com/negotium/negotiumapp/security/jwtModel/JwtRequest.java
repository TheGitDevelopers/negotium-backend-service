package com.negotium.negotiumapp.security.jwtModel;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 6088547344014072660L;

    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
