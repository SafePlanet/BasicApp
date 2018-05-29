package com.spi.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ErrorMessage {
    
	@JsonIgnore
    private HttpStatus status;
    private String message = null;
    private String name = null;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage() {
    }

    private ErrorMessage(Builder builder) {
        this.name = builder.name;
        this.status = builder.status;
        this.message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name = "Default error name";
        private HttpStatus status;
        private String message = "No message available.";

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorMessage build() {
            return new ErrorMessage(this);
        }
    }

    
}
