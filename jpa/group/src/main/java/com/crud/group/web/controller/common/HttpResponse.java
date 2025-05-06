package com.crud.group.web.controller.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public  abstract class HttpResponse {
    @JsonIgnore
    private final int statusCode;
    @JsonIgnore
    private final String meesage;

    protected HttpResponse(int statusCode, String meesage) {
        this.statusCode = statusCode;
        this.meesage = meesage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMeesage() {
        return meesage;
    }
    @JsonIgnore
    public boolean isSuccessFull(){return statusCode >= 200 && statusCode < 300;}
    @JsonIgnore
    public boolean isClientError(){return statusCode >= 400 && statusCode < 500;}
    @JsonIgnore
    public boolean isServerError(){return statusCode >= 500;}

}
