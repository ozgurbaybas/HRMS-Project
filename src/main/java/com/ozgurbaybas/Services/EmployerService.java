package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.UserActivation;

import java.util.List;

public interface EmployerService {
    Result add(Employer employer);
    Result update(Employer employer);
    Result delete(Employer employer);
    DataResult<List<Employer>> getAll();
    DataResult<Employer> getById(int id);
    Result activate(UserActivation userActivation);
}