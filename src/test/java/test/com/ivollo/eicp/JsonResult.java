package test.com.ivollo.eicp;

import lombok.Data;

@Data
public class JsonResult {
    private int code;
    private String msg;
    private Object data;
    private boolean success;
}
