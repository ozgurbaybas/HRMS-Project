package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Repository.JobPostingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class JobPostingServiceImpl implements JobPostingService {

    private JobPostingRepository jobPostingRepository;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    @Override
    public Result add(JobPosting jobPosting) {

        jobPosting.setPostingDate(LocalDate.now());
        jobPosting.setActive(false);

        jobPostingRepository.save(jobPosting);
        return new SuccessResult("Job posting added.");
    }

    @Override
    public Result update(JobPosting jobPosting) {

        jobPostingRepository.save(jobPosting);
        return new SuccessResult("Job posting has been updated.");
    }

    @Override
    public Result delete(JobPosting jobPosting) {

        jobPostingRepository.delete(jobPosting);
        return new SuccessResult("Job posting deleted.");
    }

    @Override
    public DataResult<List<JobPosting>> getAll() {
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.findAll());
    }

    @Override
    public DataResult<JobPosting> getById(int id) {
        return new SuccessDataResult<JobPosting>(jobPostingRepository.getById(id));
    }

    @Override
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails() {
        return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingRepository.getJobPostingWithEmployerAndJobTitleDtoByIsActive(true));
    }

    /*
    @Override
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate() {

        Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");

        return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDtoByIsActive(true, sort));
    }
    */

    @Override
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingRepository.getJobPostingWithEmployerAndJobTitleDtoByIsActiveAndCompanyName(true, companyName));
    }

    @Override
    public Result doActiveOrPassive(int id, boolean isActive) {

        String statusMessage;

        if (isActive) {
            statusMessage = "The ad has been activated.";
        } else {
            statusMessage = "The ad has been deactivated.";
        }

        JobPosting jobPosting = getById(id).getData();
        jobPosting.setActive(isActive);

        update(jobPosting);
        return new SuccessResult(statusMessage);
    }
}