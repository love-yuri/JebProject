package com.yuri.school.result;

import lombok.Data;

@Data
public class R<T> {
    public R(Boolean isSuccess, T data, String msg) {
        this.isSuccess = isSuccess;
        this.data = data;
        this.msg = msg;
    }

    public R(T data, String msg) {
        this.isSuccess = true;
        this.msg = msg;
        this.data = data;
    }

    private Boolean isSuccess; // 指令是否执行成功
    private String msg; // 回执消息
    private T data; // 返回的消息

    public static <E> R<E> success(E e) {
        return new R<E>(e, "ok");
    }

    public static <E> R<E> error(E e, String msg) {
        return new R<E>(false, e, msg);
    }
}
