package com.newbiebe.chatty.dto;

public class ResponseWrapper {
    private final String message;
    private final Object data;

    public ResponseWrapper(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
