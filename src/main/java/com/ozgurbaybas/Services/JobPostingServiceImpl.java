package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Models.JobPostingConfirmation;
import com.ozgurbaybas.Repository.JobPostingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
        return new SuccessResult("The job posting is in the approval phase.");
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
        if (!isConfirmed) {
            delete(jobPosting);
            return new ErrorResult("The job posting was not approved.");
        }
        jobPosting.setConfirmed(isConfirmed);
        jobPostingRepository.save(jobPosting);
        jobPostingConfirmationService.add(new JobPostingConfirmation(jobPosting, companyStaff));
        return new SuccessResult("Job posting approved.");
    }
    @Override
    public Result doActiveOrPassive(int id, boolean isActive) {
        String statusMessage = isActive ? "The ad has been activated." : "The ad has been deactivated.";
        JobPosting jobPosting = getById(id).getData();
        jobPosting.setActive(isActive);
        update(jobPosting);
        return new SuccessResult(statusMessage);
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnes(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true, pageable));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("postingDate").descending());
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true, pageable));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6() {
        List<JobPosting> result = getAllActiveOnesSortedByPostingDate(1, 6).getData();
        return new SuccessDataResult<List<JobPosting>>(result);
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId) {
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActiveAndEmployer_Id(true, employerId));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(
            int workingTimeId, int workingTypeId, int cityId, int pageNo, int pageSize) {

        Stream<JobPosting> stream = getAllActiveOnes(pageNo, pageSize).getData().stream();

        Predicate<JobPosting> workingTimeCondition = jobPosting -> jobPosting.getWorkingTime().getId() == workingTimeId;
        Predicate<JobPosting> workingTypeCondition = jobPosting -> jobPosting.getWorkingType().getId() == workingTypeId;
        Predicate<JobPosting> cityCondition = jobPosting -> jobPosting.getCity().getId() == cityId;
        List<JobPosting> result = new ArrayList<JobPosting>();
        if (workingTimeId == 0 && workingTypeId != 0 && cityId != 0) {
            stream.filter(workingTypeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId != 0 && workingTypeId == 0 && cityId != 0) {
            stream.filter(workingTimeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId != 0 && workingTypeId != 0 && cityId == 0) {
            stream.filter(workingTimeCondition).filter(workingTypeCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId == 0 && workingTypeId == 0 && cityId != 0) {
            stream.filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId == 0 && workingTypeId != 0 && cityId == 0) {
            stream.filter(workingTypeCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId != 0 && workingTypeId == 0 && cityId == 0) {
            stream.filter(workingTimeCondition).forEach(jobPosting -> result.add(jobPosting));
        } else if (workingTimeId != 0 && workingTypeId != 0 && cityId != 0) {
            stream.filter(workingTimeCondition).filter(workingTypeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
        } else {
            return new SuccessDataResult<List<JobPosting>>(getAllActiveOnes(pageNo, pageSize).getData());
        }

        return new SuccessDataResult<List<JobPosting>>(result);
    }
}