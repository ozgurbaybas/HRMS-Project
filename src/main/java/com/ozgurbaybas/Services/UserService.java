package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.User;

import java.util.List;

public interface UserService {

    DataResult<List<User>> getAll();
    DataResult<User> getById(int id);

}