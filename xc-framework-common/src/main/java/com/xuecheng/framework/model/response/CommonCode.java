package com.xuecheng.framework.model.response;

import lombok.ToString;


@ToString
public enum CommonCode implements ResultCode{

    REQUEST_METHOD_ERROR(false,10004,"请求方式错误"),
    INVAILD_PARAM(false,10003,"非法参数!"),
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    COUSRSE_DENIED_DELETE(false,10005,""),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");

   // private static ImmutableMap<Integer, CommonCode> codes ;

    //操作是否成功
    private boolean success;
    //操作代码
    private int code;
    //提示信息
    private String message;

    CommonCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
