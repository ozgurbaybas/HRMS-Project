package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.UserActivation;

import java.util.List;

public interface UserActivationService {
    Result add(UserActivation userActivation);
    Result update(UserActivation userActivation);
    Result delete(int id);
    DataResult<List<UserActivation>> getAll();
    DataResult<UserActivation> getById(int id);
    DataResult<UserActivation> getByCode(String code);
    //DataResult<UserActivation> getByUser(Employer user);
    DataResult<UserActivation> getByUserId(int userId);
}