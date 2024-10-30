package com.pgoncharova.taxfiling.system;

/**
 * Defines response schema. Encapsulates data prepared server-side. This object will be serialized to JSON before it is
 * sent back to client.
 */
public class Result {

    private boolean flag; // True means success

    private Integer code; // Status code (eg. 200)

    private String message; // Response message

    private Object data; // Response payload

    public Result() {}

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
