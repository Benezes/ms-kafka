package br.com.mnz.product.controller.handler;

import br.com.mnz.product.controller.handler.utils.StandardError;
import br.com.mnz.product.service.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> postNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {

        StandardError error = StandardError
                .builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
