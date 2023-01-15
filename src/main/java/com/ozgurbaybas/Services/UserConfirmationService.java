package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserConfirmation;

import java.util.List;

public interface UserConfirmationService {

    Result add(UserConfirmation userConfirmation);
    Result update(UserConfirmation userConfirmation);
    Result delete(int id);
    DataResult<List<UserConfirmation>> getAll();
    DataResult<UserConfirmation> getById(int id);
    DataResult<List<UserConfirmation>> getAllByUserId(int userId);
}