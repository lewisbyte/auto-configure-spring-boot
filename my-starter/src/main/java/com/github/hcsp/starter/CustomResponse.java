package com.github.hcsp.starter;

public class CustomResponse {
    private String status;
    private Object data;

    public CustomResponse(Object data) {
        this.status = "ok";
        this.data = data;

    }

    public CustomResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
