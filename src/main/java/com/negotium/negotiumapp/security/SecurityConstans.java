package com.negotium.negotiumapp.security;

public final class SecurityConstans {

    public static final String AUTH_LOGIN_URL = "/api/user/authenticate";
    public static final String PUBLIC_URL = "/api/public";
    public static final String SECURED_URL = "/secured";
    public static final String USER_URL = "/api/user";
    public static final String API_URL = "/api";
    public static final String REGISTER_URL = "/api/user/register";
    public static final String API_EMPLOYEE = "/api/employee";
    public static final String API_PRODUCTS = "/api/product";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private SecurityConstans() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}