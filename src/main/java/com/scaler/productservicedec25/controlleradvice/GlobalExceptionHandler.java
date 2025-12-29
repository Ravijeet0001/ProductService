package com.scaler.productservicedec25.controlleradvice;

import com.scaler.productservicedec25.dtos.ExceptionDto;
import com.scaler.productservicedec25.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> productNotFounderHandler(ProductNotFoundException ex){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product not found");
        exceptionDto.setSolution("Try with valid id: " + ex.getProductId());

        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.NOT_FOUND
        );

    }

}
