package com.sapo.ex7restfulapispring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerResource extends ResponseEntityExceptionHandler {

    // Xử lí ngoại lệ chưa rõ
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(
            Exception ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Error!",
                details);

        return ResponseEntityBuilder.build(err);
    }

    // Xử lí ngoại lệ trùng mã
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> duplicateCode(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Dulicate Code Product",
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> notFoundID(
            NoSuchElementException ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Not found!",
                details);

        return ResponseEntityBuilder.build(err);
    }

    // mapping sai
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Method Not Found",
                details);

        return ResponseEntityBuilder.build(err);

    }

    // báo cáo kết quả ràng buộc của các thuộc tính
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(
            Exception ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Constraint Violations",
                details);

        return ResponseEntityBuilder.build(err);
    }

    // tham số sai kiểu
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Type Mismatch",
                details);

        return ResponseEntityBuilder.build(err);
    }

    //tham số chưa đúng
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details);

        return ResponseEntityBuilder.build(err);
    }
}
