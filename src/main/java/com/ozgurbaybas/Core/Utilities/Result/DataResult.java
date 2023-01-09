package com.ozgurbaybas.Core.Utilities.Result;

import com.ozgurbaybas.Models.User;

public class DataResult<T> extends Result {

    private T data;

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public User getData() {
        return (User) this.data;
    }

}