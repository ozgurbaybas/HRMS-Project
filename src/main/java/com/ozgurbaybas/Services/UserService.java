package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Entities.User;
import com.ozgurbaybas.Core.Utilities.Result.DataResult;


import java.util.List;

public interface UserService {

    DataResult<List<User>> getAll();
    DataResult<User> getById(int id);
    DataResult<User> getByEmail(String email);
}