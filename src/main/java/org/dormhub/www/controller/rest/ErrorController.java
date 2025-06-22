package org.dormhub.www.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rs.ErrorResponse;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception e) {
        log.error("Внутрення ошибка сервера: ", e);
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Внутрення ошибка сервера: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleError(ResourceNotFoundException e) {
        log.error("Ресурс не найден: ", e);
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Ресурс не найден: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleError(IllegalArgumentException e) {
        log.error("Недопустимое значение: ", e);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Недопустимые значения: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleError(MethodArgumentNotValidException e) {
        log.error("Невалидный параметр: ", e);

        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .limit(1)
                .collect(Collectors.joining());

        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Невалидный параметр: " + errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
