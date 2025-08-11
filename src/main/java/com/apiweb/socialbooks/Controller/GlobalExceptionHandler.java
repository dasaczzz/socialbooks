package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Lib.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
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
    public ResponseEntity<BaseResponse> handleHttpMessageNotReadableException(WebRequest request) {
        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Required request JSON body is missing",
            BaseResponse.getMethodFromRequest(request),
            BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<BaseResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, WebRequest request) {
        BaseResponse errorDetails = new BaseResponse(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                "Bad Content type. Not supported",
                BaseResponse.getMethodFromRequest(request),
                BaseResponse.getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
