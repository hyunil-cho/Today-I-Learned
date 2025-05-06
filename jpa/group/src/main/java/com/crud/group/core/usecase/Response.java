package com.crud.group.core.usecase;

public class Response {

    private final int statusCode;
    private final String meesage;
    public Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.meesage = message;
    }

    public String getMeesage() {
        return meesage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
