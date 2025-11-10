package com.example.matchapi.model;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Getter
public enum Sport {
    FOOTBALL(1),
    BASKETBALL(2);

    private final int code;

    Sport(int code) {
        this.code = code;
    }

    public static Sport fromCode(int code) {
        for (Sport sport : Sport.values()) {
            if (sport.getCode() == code) {
                return sport;
            }
        }
        throw new IllegalArgumentException("Invalid Sport code: " + code);
    }

    @JsonCreator
    public static Sport fromString(String value) {
        if (value == null) return null;
        try {
            return Sport.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid sport value: " + value);
        }
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }
}

