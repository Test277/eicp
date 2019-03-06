package test.okhttp;

import lombok.Data;

@Data
public class IResponse {
    private int code;
    private String msg;
    private Object data;
    private boolean success;
}
