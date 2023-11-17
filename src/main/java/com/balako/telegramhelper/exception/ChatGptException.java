package com.balako.telegramhelper.exception;

public class ChatGptException extends RuntimeException {
    public ChatGptException(String message, Throwable e) {
        super(message, e);
    }
}
