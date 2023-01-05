package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;

import java.util.List;

public interface BaseEntityService<T> {

    Result add(T entity);
    Result update(T entity);
    Result delete(T entity);
    DataResult<List<T>> getAll();
    DataResult<T> getById(int id);
}