package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.JobPostingConfirmationType;
import com.ozgurbaybas.Repository.JobPostingConfirmationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingConfirmationTypeServiceImpl implements JobPostingConfirmationTypeService {

    private JobPostingConfirmationTypeRepository jobPostingConfirmationTypeRepository;

    @Autowired
    public JobPostingConfirmationTypeServiceImpl(JobPostingConfirmationTypeRepository jobPostingConfirmationTypeRepository) {
        this.jobPostingConfirmationTypeRepository = jobPostingConfirmationTypeRepository;
    }

    @Override
    public Result add(JobPostingConfirmationType entity) {
        return new SuccessResult("Job posting approval type added.");
    }

    @Override
    public Result update(JobPostingConfirmationType entity) {
        return new SuccessResult("Job posting approval type has been updated.");
    }

    @Override
    public Result delete(int id) {
        return new SuccessResult("Job posting approval type has been deleted.");
    }

    @Override
    public DataResult<List<JobPostingConfirmationType>> getAll() {
        return new SuccessDataResult<List<JobPostingConfirmationType>>(jobPostingConfirmationTypeRepository.findAll());
    }

    @Override
    public DataResult<JobPostingConfirmationType> getById(int id) {
        return new SuccessDataResult<JobPostingConfirmationType>(jobPostingConfirmationTypeRepository.getById(id));
    }

}