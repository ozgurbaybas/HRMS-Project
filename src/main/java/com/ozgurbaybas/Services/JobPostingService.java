package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPosting;


import java.util.List;

public interface JobPostingService extends BaseEntityService<JobPosting> {

    Result confirm(int employerId, int companyStaffId, boolean isConfirmed);
    Result doActiveOrPassive(int id, boolean isActive);
    DataResult<List<JobPosting>> getAllActiveJobPosting(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveJobPostingSortedByPostingDate(int pageNo, int pageSize);
    DataResult<List<JobPosting>> getAllActiveJobPostingSortedByPostingDateTop6();
    DataResult<List<JobPosting>> getAllActiveJobPostingByEmployerId(int employerId);
    DataResult<List<JobPosting>> getAllActiveJobPostingFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId, int pageNo, int pageSize);

}

