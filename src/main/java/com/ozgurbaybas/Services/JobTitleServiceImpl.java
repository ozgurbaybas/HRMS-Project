package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.JobTitle;
import com.ozgurbaybas.Repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleServiceImpl implements JobTitleService {

    private JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository) {
        super();
        this.jobTitleRepository = jobTitleRepository;
    }

    @Override
    public DataResult<List<JobTitle>> getAll() {
        return new SuccessDataResult<List<JobTitle>>(this.jobTitleRepository.findAll());
    }

    @Override
    public Result add(JobTitle jobTitle) {

        if(!checkIfJobTitleExists(jobTitle.getTitle())) {
            this.jobTitleRepository.save(jobTitle);
            return new SuccessResult("Job position added.");
        }

        return new ErrorResult("The job position you want to add already exists.");
    }

    @Override
    public Result update(JobTitle jobTitle) {
        this.jobTitleRepository.save(jobTitle);
        return new SuccessResult("Job position updated.");
    }

    @Override
    public Result delete(JobTitle jobTitle) {
        this.jobTitleRepository.delete(jobTitle);
        return new SuccessResult("The job position has been deleted.");
    }

    @Override
    public DataResult<JobTitle> getById(Long id) {
        return new SuccessDataResult<JobTitle>(this.jobTitleRepository.getById(id));
    }

    public boolean checkIfJobTitleExists(String title) {

        boolean result = false;

        for (JobTitle jobTitle : getAll().getData()) {
            if (jobTitle.getTitle() == title) {
                result = true;
            }
        }
        return result;
    }
}