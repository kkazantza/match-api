package com.example.matchapi.advice;

public class MatchOddsNotFoundException extends RuntimeException {
    public MatchOddsNotFoundException(String message) {
        super(message);
    }
}
