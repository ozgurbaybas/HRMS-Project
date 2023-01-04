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

        if (!userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(),
                candidate.getLastName(), candidate.getYearOfBirth())) {
            return new ErrorResult("Please enter your information correctly.");
        }

        if (!checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
            return new ErrorResult("The entered ID number belongs to another account.");
        }

        candidate.setActivated(false);
        candidateRepository.save(candidate);
        return userActivationService.add(new UserActivation(candidate));
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
    public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
        return new SuccessDataResult<Candidate>(candidateRepository.getByIdentityNumber(identityNumber));
    }

    @Override
    public Result activate(String code) {

        UserActivation userActivation = userActivationService.getByCode(code).getData();

        if (userActivation == null) {
            return new ErrorResult("Geçersiz bir kod girdiniz.");
        }

        getById(userActivation.getUser().getId()).getData().setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationService.update(userActivationService.getByCode(code).getData());
        return new SuccessResult("Membership procedures have been completed.");
    }

    private boolean checkIfIdentityNumberExists(String identityNumber) {

        boolean result = false;

        if (getByIdentityNumber(identityNumber).getData() == null) {
            result = true;
        }

        return result;
    }
}