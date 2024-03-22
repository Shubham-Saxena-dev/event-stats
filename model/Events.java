package com.hf.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

@JsonDeserialize(builder = Events.Builder.class)
public class Events implements Serializable {

    private final long timestamp;
    private final double x;
    private final int y;

    public Events(Builder builder) {
        this.timestamp = builder.timeStamp;
        this.x = builder.x;
        this.y = builder.y;
    }

    public long timeStamp() {
        return timestamp;
    }

    public double x() {
        return x;
    }

    public int y() {
        return y;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private long timeStamp;
        private double x;
        private int y;

        public Builder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder x(double x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Events build() {
            return new Events(this);
        }
    }
}
