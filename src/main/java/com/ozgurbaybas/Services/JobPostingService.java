package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPosting;


import java.util.List;

public interface JobPostingService extends BaseEntityService<JobPosting> {
    Result confirm(int employerId, int companyStaffId, boolean isConfirmed);
    Result doActiveOrPassive(int id, boolean isActive);
    DataResult<List<JobPosting>> getAllActiveOnes();
    DataResult<List<JobPosting>> getAllActiveOnesByPage(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate();
    DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6();
    DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId);
    DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId);
    DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId, int pageNo, int pageSize);

}

