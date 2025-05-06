package com.crud.group.core.usecase;

public abstract class Resultable {
    private final Response response;

    protected Resultable(int statusCode, String message) {
        this.response = new Response(statusCode, message);
    }

    public Response getResponse() {
        return response;
    }
}
