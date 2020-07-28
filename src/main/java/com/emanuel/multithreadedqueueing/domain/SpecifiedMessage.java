package com.emanuel.multithreadedqueueing.domain;

public class SpecifiedMessage {

    private final String message;
    private final SpecifiedMessageType messageType;

    public SpecifiedMessage(String message, SpecifiedMessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public SpecifiedMessageType getMessageType() {
        return messageType;
    }
}
