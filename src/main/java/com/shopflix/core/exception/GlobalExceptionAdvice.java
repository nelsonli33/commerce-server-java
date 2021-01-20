package com.shopflix.core.exception;

import com.shopflix.core.response.ApiError;
import com.shopflix.core.response.ApiResult;
import com.shopflix.core.response.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {


    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Object> handleModelNotFoundException(ModelNotFoundException ex) {
        ApiError error = new ApiError(ErrorCode.ERROR_CODE_1000, ex.getMessage());
        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
    }

    @ExceptionHandler(ParentSelfRefException.class)
    public ResponseEntity<Object> handleParentSelfReferenceException(ParentSelfRefException ex) {
        ApiError error = new ApiError(ErrorCode.ERROR_CODE_1020, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(ConversionException.class)
    public ResponseEntity<Object> handleConversionException(ConversionException ex) {
        ApiError error = new ApiError(ErrorCode.ERROR_CODE_1010, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
