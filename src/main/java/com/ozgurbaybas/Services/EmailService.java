package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.User;

public interface EmailService {

    Result sendEmail(User user);

}