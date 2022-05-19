package org.iqmanager.config.security.jwt;

public class AuthResultData {

    private final String message;

    public AuthResultData(String token) {
        this.message = token;
    }

    public AuthResultData() {
        this.message = "OK";
    }

    public String getMessage() {
        return message;
    }

}
