package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Models.JobPostingConfirmation;
import com.ozgurbaybas.Models.JobPostingConfirmationType;
import com.ozgurbaybas.Repository.JobPostingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    private JobPostingRepository jobPostingRepository;
    private JobPostingConfirmationService jobPostingConfirmationService;
    private JobPostingConfirmationTypeService jobPostingConfirmationTypeService;
    private CompanyStaffService companyStaffService;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, JobPostingConfirmationService jobPostingConfirmationService, JobPostingConfirmationTypeService jobPostingConfirmationTypeService, CompanyStaffService companyStaffService) {
        this.jobPostingRepository = jobPostingRepository;
        this.jobPostingConfirmationService = jobPostingConfirmationService;
        this.jobPostingConfirmationTypeService = jobPostingConfirmationTypeService;
        this.companyStaffService = companyStaffService;
    }

    @Override
    public Result add(JobPosting jobPosting) {
        jobPosting.setPostingDate(LocalDateTime.now());
        jobPosting.setActive(false);
        jobPostingRepository.save(jobPosting);
        return new SuccessResult("The job posting is in the approval phase.");
    }

    @Override
    public Result update(JobPosting jobPosting) {
        jobPostingRepository.save(jobPosting);
        return new SuccessResult("Job posting has been updated.");
    }
    @Override
    public Result delete(int id) {
        jobPostingRepository.deleteById(id);
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
    public Result confirm(int jobPostingId, int companyStaffId, int jobPostingConfirmationTypeId, boolean isConfirmed) {
        JobPosting jobPosting = getById(jobPostingId).getData();
        CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
        JobPostingConfirmationType jobPostingConfirmationType = jobPostingConfirmationTypeService.getById(jobPostingConfirmationTypeId).getData();
        if (!isConfirmed) {
            delete(jobPosting.getId());
            return new ErrorResult("The job posting was not approved.");
        }
        jobPosting.setActive(true);
        jobPostingRepository.save(jobPosting);
        jobPostingConfirmationService.add(new JobPostingConfirmation(jobPosting, companyStaff, jobPostingConfirmationType, isConfirmed));
        return new SuccessResult("Job posting approved.");
    }

    @Override
    public Result makeActiveOrPassive(int id, boolean isActive) {
        String statusMessage = isActive ? "The ad has been activated." : "The ad has been deactivated.";
        JobPosting jobPosting = getById(id).getData();
        jobPosting.setActive(isActive);
        update(jobPosting);
        return new SuccessResult(statusMessage);
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnes() {
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesByPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true, pageable));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate() {

        Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");

        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true, sort));
    }
    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("postingDate").descending());
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActive(true, pageable));
    }
    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6() {
        List<JobPosting> result = getAllActiveOnesByPageSortedByPostingDate(1, 6).getData();
        return new SuccessDataResult<List<JobPosting>>(result);
    }
    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId) {
        return new SuccessDataResult<List<JobPosting>>(jobPostingRepository.getByIsActiveAndEmployer_Id(true, employerId));
    }

    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId) {

        List<JobPosting> result = getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingTypeBase(cityId, jobTitleId, workingTimeId, workingTypeId);

        return new SuccessDataResult<List<JobPosting>>(result);
    }
    @Override
    public DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId, int pageNo, int pageSize) {

        int skipCount = (pageNo -1) * pageSize;

        List<JobPosting> result = getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingTypeBase(cityId, jobTitleId, workingTimeId, workingTypeId);
        return new SuccessDataResult<List<JobPosting>>(result.stream().skip(skipCount).limit(pageSize).collect(Collectors.toList()));
    }

    private List<JobPosting> getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingTypeBase(int cityId, int jobTitleId, int workingTimeId, int workingTypeId) {

        List<JobPosting> result = new ArrayList<JobPosting>();

        Stream<JobPosting> stream = getAllActiveOnesSortedByPostingDate().getData().stream();

        Predicate<JobPosting> cityCondition = null;
        Predicate<JobPosting> jobTitleCondition = null ;
        Predicate<JobPosting> workingTimeCondition = null ;
        Predicate<JobPosting> workingTypeCondition = null;

        cityCondition = cityId != 0 ? (jobPosting -> jobPosting.getCity().getId() == cityId) : (jobPosting -> jobPosting.getCity().getId() > 0);
        jobTitleCondition = jobTitleId != 0 ? (jobPosting -> jobPosting.getJobTitle().getId() == jobTitleId) : (jobPosting -> jobPosting.getJobTitle().getId() > 0);
        workingTimeCondition = workingTimeId != 0 ? (jobPosting -> jobPosting.getWorkingTime().getId() == workingTimeId) : (jobPosting -> jobPosting.getWorkingTime().getId() > 0);
        workingTypeCondition = workingTypeId != 0 ? (jobPosting -> jobPosting.getWorkingType().getId() == workingTypeId) : (jobPosting -> jobPosting.getWorkingType().getId() > 0);

        stream.filter(workingTimeCondition).filter(workingTypeCondition).filter(cityCondition).filter(jobTitleCondition).forEach(jobPosting -> result.add(jobPosting));

        return result;
    }
}