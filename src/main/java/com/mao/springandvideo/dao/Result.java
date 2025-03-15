package com.mao.springandvideo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;        // 状态码
    private String message;  // 信息
    private T data;         // 数据

    // 成功的响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "请求成功", data);
    }

    // 失败的响应
    public static Result<Void> error(String message) {
        return new Result<>(400, message, null);
    }
}

