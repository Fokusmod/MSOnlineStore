package com.geekbrains.callservice.exception;

import java.util.List;

public class CallRequestException extends RuntimeException {

    private List<String> messages;

    public CallRequestException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
