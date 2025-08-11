package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Lib.BaseResponse;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<BaseResponse> handleResourceNotFound(ResourceNotFound e,  WebRequest request) {
        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            BaseResponse.getMethodFromRequest(request),
            BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleBadBody(HttpMessageNotReadableException e, WebRequest request) {
        Throwable cause = e.getCause();

        if (cause instanceof ValueInstantiationException) {
            BaseResponse errorResponse = new BaseResponse(
                HttpStatus.BAD_REQUEST.value(),
                "The id in the request body has not been found",
                BaseResponse.getPathFromRequest(request),
                BaseResponse.getMethodFromRequest(request)
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Required request body JSON is missing",
            BaseResponse.getMethodFromRequest(request),
            BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse> handleBadJson(WebRequest request) {
        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.BAD_REQUEST.value(),
            "The request body JSON has missing properties",
            BaseResponse.getPathFromRequest(request),
            BaseResponse.getMethodFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<BaseResponse> handleBadContentType(WebRequest request) {
        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
            "Bad Content type. Not supported",
            BaseResponse.getMethodFromRequest(request),
            BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(WebRequest request) {
        BaseResponse errorsResponse = new BaseResponse(
            HttpStatus.BAD_REQUEST.value(),
            "The request body JSON is not valid",
            BaseResponse.getMethodFromRequest(request),
            BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);
    }
}
