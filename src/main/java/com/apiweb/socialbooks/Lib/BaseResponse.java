package com.apiweb.socialbooks.Lib;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    protected int status;
    protected String message;
    protected String method;
    protected String path;
    protected String timestamp;

    public BaseResponse(int status, String message, String method, String path) {
        this.status = status;
        this.message = message;
        this.method = method;
        this.path = path;
        this.timestamp = Utils.getLocalDateTime();
    }

    public static String getPathFromRequest(WebRequest request) {
        String description = request.getDescription(false);
        if (description.startsWith("uri=")) {
            return description.substring(4);
        }
        return description;
    }

    public static String getMethodFromRequest(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
            return httpServletRequest.getMethod();
        }
        return null;
    }
}
