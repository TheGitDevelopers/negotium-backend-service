package com.negotium.negotiumapp.security.v1.jwtModel;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 4824321880392445566L;

    private final String jwtToken;

    public JwtResponse(String jwtoken) {
        this.jwtToken = jwtoken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}