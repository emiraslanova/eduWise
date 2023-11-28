package com.example.eduwise.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class BaseResponse <T>{
    String message;
    HttpStatus httpStatus;
    T data;

    public BaseResponse() {
    }

    public BaseResponse(String message, HttpStatus httpStatus, T data) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", data=" + data +
                '}';
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponseBuilder<T>().message("success").
                httpStatus(HttpStatus.OK).
                data(data).
                build();
    }
    public static <T> BaseResponse<T> fail() {
        return new BaseResponseBuilder<T>().message("fail").
                httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).
                build();
    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponseBuilder<T>().message("ok").
                httpStatus(HttpStatus.OK).
                data(data).
                build();
    }
}

