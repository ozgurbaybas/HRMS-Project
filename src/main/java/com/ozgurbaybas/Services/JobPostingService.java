package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPosting;


import java.util.List;

public interface JobPostingService extends BaseEntityService<JobPosting> {

    Result confirm(int employerId, int companyStaffId, boolean isConfirmed);
    DataResult<List<JobPosting>> getAllActiveOnes();
    DataResult<List<JobPosting>> getAllActiveOnesByPage(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate();
    DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6();
    DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId);
    DataResult<List<JobPosting>> getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId);
    DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId, int pageNo, int pageSize);
    Result makeActiveOrPassive(int id, boolean isActive);
}

