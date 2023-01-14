package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto;
import com.ozgurbaybas.Models.JobPosting;

import java.util.List;

public interface JobPostingService {

    Result add(JobPosting jobPosting);
    Result update(JobPosting jobPosting);
    Result delete(JobPosting jobPosting);
    DataResult<List<JobPosting>> getAll();
    DataResult<JobPosting> getById(int id);
    DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails();
    DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName);

    //DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate();

    Result doActiveOrPassive(int id, boolean isActive);
    Result confirm(int employerId, int companyStaffId, boolean isConfirmed);
    DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDateTop6();
}