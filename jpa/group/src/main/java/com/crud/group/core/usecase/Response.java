package com.crud.group.core.usecase;

public abstract class Response {

    private final int statusCode;
    private final String meesage;
    protected Response(int statusCode, String message) {
        this.statusCode = statusCode;
        this.meesage = message;
    }

    public String getMeesage() {
        return meesage;
    }

    public boolean isSuccessFull(){return statusCode >= 200 && statusCode < 300;}
    public boolean isClientError(){return statusCode >= 400 && statusCode < 500;}
    public boolean isServerError(){return statusCode >= 500;}
}
