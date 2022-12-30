package com.ozgurbaybas.Services;

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
    public List<JobTitle> getAll() {
        return this.jobTitleRepository.findAll();
    }
}