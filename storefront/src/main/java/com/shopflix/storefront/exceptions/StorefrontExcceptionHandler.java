package com.shopflix.storefront.exceptions;

import com.shopflix.core.response.ApiResult;
import com.shopflix.core.response.ErrorCode;
import com.shopflix.core.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StorefrontExcceptionHandler {

    @ExceptionHandler(DuplicateUidException.class)
    public ResponseEntity<ErrorResponse> handleConversionException(DuplicateUidException ex) {
        ErrorResponse error = ApiResult.error(ErrorCode.E_3001, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
