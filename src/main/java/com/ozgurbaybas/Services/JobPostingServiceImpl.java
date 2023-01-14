package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Models.JobPostingConfirmation;
import com.ozgurbaybas.Repository.JobPostingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JobPostingServiceImpl implements JobPostingService {

    private JobPostingRepository jobPostingRepository;
    private JobPostingConfirmationService jobPostingConfirmationService;
    private CompanyStaffService companyStaffService;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, JobPostingConfirmationService jobPostingConfirmationService, CompanyStaffService companyStaffService) {
        this.jobPostingRepository = jobPostingRepository;
        this.jobPostingConfirmationService = jobPostingConfirmationService;
        this.companyStaffService = companyStaffService;

    }

    @Override
    public Result add(JobPosting jobPosting) {

        jobPosting.setPostingDate(LocalDateTime.now());
        jobPosting.setActive(false);
        jobPosting.setConfirmed(false);
        jobPostingRepository.save(jobPosting);
        return new SuccessResult("Job posting is under approval.");
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
    public Result confirm(int jobPostingId, int companyStaffId, boolean isConfirmed) {

        JobPosting jobPosting = getById(jobPostingId).getData();
        CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();

        if(!isConfirmed) {
            delete(jobPosting);
            return new ErrorResult("The job posting was not approved.");
        }

        jobPosting.setConfirmed(isConfirmed);

        update(jobPosting);
        jobPostingConfirmationService.add(new JobPostingConfirmation(jobPosting, companyStaff));
        return new SuccessResult("Job posting approved.");
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
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDateTop6() {

        List<JobPostingWithEmployerAndJobTitleDto> result = getAllActiveJobPostingDetailsSortedByPostingDate().getData();

        List<JobPostingWithEmployerAndJobTitleDto> listTop6 = new ArrayList<JobPostingWithEmployerAndJobTitleDto>();
        listTop6.add(result.get(0));
        listTop6.add(result.get(1));
        listTop6.add(result.get(2));
        listTop6.add(result.get(3));
        listTop6.add(result.get(4));
        listTop6.add(result.get(5));

        return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(listTop6);
    }

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