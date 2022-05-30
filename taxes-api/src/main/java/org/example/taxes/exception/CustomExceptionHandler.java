package org.example.taxes.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse<Void>> handleException(ResourceNotFoundException ex) {
        ExceptionResponse<Void> res = new ExceptionResponse<Void>();
        res.setStatus(HttpStatus.NOT_FOUND.value());
        res.setError(String.format("Resource not found: %s", ex.getMessage()));
        LOG.error(ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingParameterException.class)
    public final ResponseEntity<ExceptionResponse<Void>> handleException(MissingParameterException ex) {
        ExceptionResponse<Void> res = new ExceptionResponse<Void>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(String.format("Missing parameter error: %s", ex.getMessage()));
        LOG.error(ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse<Void>> handleGeneralException(Exception ex) {
        ExceptionResponse<Void> res = new ExceptionResponse<Void>();
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        res.setError(String.format("Internal Server error : %s", ex.getMessage()));
        LOG.error(ex.getMessage(), ex);
        ex.printStackTrace();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
