package ru.skypro.lessons.springboot.weblibrary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleExceptionNoId(ExceptionNoId exceptionNoId) {
        String message = "Нет работника под данным id\n";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
