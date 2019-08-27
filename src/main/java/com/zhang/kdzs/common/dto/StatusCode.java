package com.zhang.kdzs.common.dto;

public class StatusCode {
    public static final int SUCCESS = 200; // 成功
    public static final int ERROR = 201; // 失败
    public static final int UKNOWN = -1; // 未知
    public static final int SYSTEM_ERROR = 500; // 成功


    public static Result success(String message, Object object) {


        return new Result(SUCCESS, message, object);
    }

    public static Result success( Object object) {


        return new Result(SUCCESS,null, object);
    }

    public static Result success(String message) {


        return new Result(SUCCESS, message, null);
    }
}
