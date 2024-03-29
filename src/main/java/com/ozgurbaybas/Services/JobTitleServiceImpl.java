package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.JobTitle;
import com.ozgurbaybas.Repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

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

        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        return new SuccessDataResult<List<JobTitle>>(jobTitleRepository.findAll(sort));
    }

    @Override
    public Result add(JobTitle jobTitle) {

        if(!checkIfJobTitleExists(jobTitle.getTitle())) {
            return new ErrorResult("The job position you want to add already exists.");
        }
            jobTitleRepository.save(jobTitle);
            return new SuccessResult("Job position added.");
    }

    @Override
    public Result update(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
        return new SuccessResult("Job position updated.");
    }

    @Override
    public Result delete(int id) {
        jobTitleRepository.deleteById(id);
        return new SuccessResult("The job position has been deleted.");
    }

    @Override
    public DataResult<JobTitle> getById(int id) {
        return new SuccessDataResult<JobTitle>(jobTitleRepository.getById(id));
    }

    @Override
    public DataResult<JobTitle> getByTitle(String title) {
        return new SuccessDataResult<JobTitle>(jobTitleRepository.getByTitle(title));
    }

    private boolean checkIfJobTitleExists(String title) {

        boolean result = false;

        if (getByTitle(title).getData() == null) {
            result = true;
        }

        return result;
    }
}