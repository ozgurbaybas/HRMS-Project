package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Repository.CandidateRepository;
import com.ozgurbaybas.Services.Adapters.Mernis.UserCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;
    private UserCheckService userCheckService;
    private UserActivationService userActivationService;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, UserCheckService userCheckService, UserActivationService userActivationService) {
        this.candidateRepository = candidateRepository;
        this.userCheckService = userCheckService;
        this.userActivationService = userActivationService;
    }

    @Override
    public Result add(Candidate candidate) {

        if (checkIfNullField(candidate)) {
            return new ErrorResult("Please fill in the blank fields.");
        }

        if (!userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(),
                candidate.getLastName(), candidate.getYearOfBirth())) {
            return new ErrorResult("Please enter your information correctly.");
        }

        if (checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
            return new ErrorResult("The entered ID number belongs to another account.");
        }

        candidateRepository.save(candidate);
        userActivationService.add(new UserActivation(candidate));
        return new SuccessResult("Activation code sent.");
    }

    @Override
    public Result update(Candidate candidate) {
        candidateRepository.save(candidate);
        return new SuccessResult();
    }

    @Override
    public Result delete(Candidate candidate) {
        candidateRepository.delete(candidate);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<List<Candidate>>(candidateRepository.findAll());
    }

    @Override
    public DataResult<Candidate> getById(int id) {
        return new SuccessDataResult<Candidate>(candidateRepository.getById(id));
    }

    @Override
    public Result activate(UserActivation userActivation) {

        userActivation.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationService.update(userActivation);
        return new SuccessResult("Membership procedures have been completed.");
    }

    private boolean checkIfNullField(Candidate candidate) {

        boolean result = false;

        if (candidate.getEmail() == null || candidate.getPassword() == null || candidate.getFirstName() == null
                || candidate.getLastName() == null || candidate.getIdentityNumber() == null
                || candidate.getYearOfBirth() == 0) {
            result = true;
        }

        return result;
    }

    private boolean checkIfIdentityNumberExists(String identityNumber) {

        boolean result = false;

        for (Candidate candidate : getAll().getData()) {
            if (candidate.getIdentityNumber() == identityNumber) {
                result = true;
            }
        }

        return result;
    }
}