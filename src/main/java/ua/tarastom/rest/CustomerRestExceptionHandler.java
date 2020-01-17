package ua.tarastom.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> customerNotFound(CustomerNotFoundException theException) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        customerErrorResponse.setErrorMessage(theException.getMessage());
        customerErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> anyError(Exception theAnyException) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        customerErrorResponse.setErrorMessage(theAnyException.getMessage());
        customerErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
