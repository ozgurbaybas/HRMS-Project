package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobTitle;

import java.util.List;

public interface JobTitleService {
    DataResult<List<JobTitle>> getAll();
    Result add(JobTitle jobTitle);
    Result update(JobTitle jobTitle);
    Result delete(JobTitle jobTitle);
    DataResult<JobTitle> getById(Long id);
    DataResult<JobTitle> getByTitle(String title);
}