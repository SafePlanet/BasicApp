package com.spi.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.mongodb.MongoWriteException;
import com.spi.exception.ErrorMessage;
import com.spi.exception.ValidationMessageException;


@ControllerAdvice
public class AdviceController {

    public static final Logger LOG = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleRestTemplateException(final HttpClientErrorException e) {
        return ResponseEntity.status(e.getStatusCode()).contentType(e.getResponseHeaders().getContentType()).body(e.getResponseBodyAsByteArray());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ValidationMessageException.class)
    public ResponseEntity<ErrorMessage> handleNotFound(final ValidationMessageException e) {
        LOG.error("Caught ValidationMessageException", e);
        ErrorMessage errorMessage = ErrorMessage.builder().withMessage(e.getErrorResponse().getConsumerMessage()).withName(HttpStatus.NOT_FOUND.name())
                .withStatus(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ErrorMessage> handleMongoDuplicate(final Exception e) {
        LOG.error("Caught ValidationMessageException", e);
        ErrorMessage errorMessage = ErrorMessage.builder().withMessage(e.getMessage()).withName(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(errorMessage);
    }
    

}