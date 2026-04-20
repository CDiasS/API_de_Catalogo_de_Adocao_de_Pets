package com.petadoption.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("status", String.valueOf(ex.getStatus().value()));
        errorDetails.put("error", ex.getStatus().getReasonPhrase());
        errorDetails.put("message", ex.getReason());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllUncaughtException(Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorDetails.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorDetails.put("message", "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        // Opcional: logar a exceção completa para depuração
        // log.error("Erro interno do servidor: ", ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
