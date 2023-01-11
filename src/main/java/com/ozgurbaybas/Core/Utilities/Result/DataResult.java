package com.ozgurbaybas.Core.Utilities.Result;

import com.ozgurbaybas.Models.JobPosting;

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

    public JobPosting getData() {
        return this.data;
    }

}