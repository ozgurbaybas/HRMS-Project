package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.JobPostingConfirmation;
import com.ozgurbaybas.Repository.JobPostingConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JobPostingConfirmationServiceImpl implements JobPostingConfirmationService {

    private JobPostingConfirmationRepository jobPostingConfirmationRepository;
    private EmailService emailService;

    @Autowired
    public JobPostingConfirmationServiceImpl(JobPostingConfirmationRepository jobPostingConfirmationRepository, EmailService emailService) {
        this.jobPostingConfirmationRepository = jobPostingConfirmationRepository;
        this.emailService = emailService;
    }

    @Override
    public Result add(JobPostingConfirmation jobPostingConfirmation) {
        jobPostingConfirmation.setIsConfirmedDate(LocalDateTime.now());
        jobPostingConfirmationRepository.save(jobPostingConfirmation);
        emailService.sendEmail(jobPostingConfirmation.getJobPosting().getEmployer());
        return new SuccessResult();
    }

    @Override
    public Result update(JobPostingConfirmation jobPostingConfirmation) {
        jobPostingConfirmationRepository.save(jobPostingConfirmation);
        return new SuccessResult();
    }

    @Override
    public Result delete(int id) {
        jobPostingConfirmationRepository.deleteById(id);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<JobPostingConfirmation>> getAll() {
        return new SuccessDataResult<List<JobPostingConfirmation>>(jobPostingConfirmationRepository.findAll());
    }

    @Override
    public DataResult<JobPostingConfirmation> getById(int id) {
        return new SuccessDataResult<JobPostingConfirmation>(jobPostingConfirmationRepository.getById(id));
    }

}