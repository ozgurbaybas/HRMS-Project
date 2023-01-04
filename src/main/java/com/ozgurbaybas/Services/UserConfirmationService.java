package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserConfirmation;

public interface UserConfirmationService {

    Result add(UserConfirmation userConfirmation);

}