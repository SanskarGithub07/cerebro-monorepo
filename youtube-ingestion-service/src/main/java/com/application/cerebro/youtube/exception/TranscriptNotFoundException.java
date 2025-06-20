package com.application.cerebro.youtube.exception;

public class TranscriptNotFoundException extends RuntimeException{
    public TranscriptNotFoundException(String message){
        super(message);
    }
}
