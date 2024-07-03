package com.ingco.anticipo.ingco_control_anticipos.global.hadlerExeptionError.controllers;

import com.ingco.anticipo.ingco_control_anticipos.global.hadlerExeptionError.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HadlerExeptionError {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> HttpMessageNotReadableException(HttpMessageNotReadableException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setDate(new Date());
        errorDto.setError("El cuerpo de la peticion no tiene el formato correcto");
        errorDto.setMessage("Required request body is missing");
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handlerNullPointer(NullPointerException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setDate(new Date());
        errorDto.setError("Elemento no encontrado o no existe");
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public  ResponseEntity<?> handlerIllegalArgument(IllegalArgumentException ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setDate(new Date());
        errorDto.setError("La petición contiene un elemento No esperado");
        errorDto.setMessage(ex.getMessage());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
