package com.apiweb.socialbooks.Lib;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
