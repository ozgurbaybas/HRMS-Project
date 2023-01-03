package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserActivation;

public interface UserActivationService {
    Result add(UserActivation userActivation);
    Result update(UserActivation userActivation);
}