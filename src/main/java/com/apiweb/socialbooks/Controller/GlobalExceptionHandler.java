package com.apiweb.socialbooks.Controller;

import com.apiweb.socialbooks.Exception.ResourceNotFound;
import com.apiweb.socialbooks.Lib.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<BaseResponse> handleResourceNotFound(ResourceNotFound e,  WebRequest request) {
        BaseResponse errorResponse = new BaseResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            getMethodFromRequest(request),
            getPathFromRequest(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private String getPathFromRequest(WebRequest request) {
        String description = request.getDescription(false);
        if (description.startsWith("uri=")) {
            return description.substring(4);
        }
        return description;
    }

    private String getMethodFromRequest(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
            return httpServletRequest.getMethod();
        }
        return null;
    }
}
