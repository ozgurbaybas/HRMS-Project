package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.Resume;
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
    private UserService userService;
    private UserCheckService userCheckService;
    private UserActivationService userActivationService;
    private ResumeService resumeService;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, UserService userService, UserCheckService userCheckService, UserActivationService userActivationService, ResumeService resumeService) {
        this.candidateRepository = candidateRepository;
        this.userService = userService;
        this.userCheckService = userCheckService;
        this.userActivationService = userActivationService;
        this.resumeService = resumeService;
    }

    @Override
    public Result add(Candidate candidate) {

        validateCandidate(candidate);
        candidateRepository.save(candidate);
        resumeService.add(new Resume(candidate));
        return userActivationService.add(new UserActivation(candidate));
    }
    @Override
    public Result update(Candidate candidate) {

        Candidate c = getById(candidate.getId()).getData();

        candidate.setEmail(candidate.getEmail() == null || candidate.getEmail() == ""
                ? c.getEmail()
                : candidate.getEmail());
        candidate.setPassword(candidate.getPassword() == null || candidate.getPassword() == ""
                ? c.getPassword()
                : candidate.getPassword());
        candidate.setFirstName(candidate.getFirstName() == null || candidate.getFirstName() == ""
                ? c.getFirstName()
                : candidate.getFirstName());
        candidate.setLastName(candidate.getLastName() == null || candidate.getLastName() == ""
                ? c.getLastName()
                : candidate.getLastName());
        candidate.setIdentityNumber(candidate.getIdentityNumber() == null || candidate.getIdentityNumber() == ""
                ? c.getIdentityNumber()
                : candidate.getIdentityNumber() );
        candidate.setDateOfBirth(candidate.getDateOfBirth() == null
                ? c.getDateOfBirth()
                : candidate.getDateOfBirth());

        validateCandidate(candidate);
        candidateRepository.save(candidate);
        return new SuccessResult("Job seeker updated.");
    }
    @Override
    public Result delete(int id) {
        candidateRepository.deleteById(id);
        return new SuccessResult("Job seeker deleted.");
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
    public Result activate(String code) {

        UserActivation userActivation = userActivationService.getByCode(code).getData();
        if (userActivation == null) {
            return new ErrorResult("You entered an invalid code.");
        }
        Candidate candidate = getById(userActivation.getUser().getId()).getData();
        userActivation.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        candidateRepository.save(candidate);
        userActivationService.update(userActivation);
        return new SuccessResult("Membership procedures have been completed.");
    }

    @Override
    public DataResult<List<Candidate>> getAllByIsActivated(boolean isActivated) {
        return new SuccessDataResult<List<Candidate>>(candidateRepository.getByUserActivation_IsActivated(isActivated));
    }

    @Override
    public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
        return new SuccessDataResult<Candidate>(candidateRepository.getByIdentityNumber(identityNumber));
    }

    private boolean checkIfEmailExists(String email) {

        boolean result = false;
        if (userService.getByEmail(email).getData() == null) {
            result = true;
        }
        return result;
    }

    private boolean checkIfIdentityNumberExists(String identityNumber) {

        boolean result = false;
        if (getByIdentityNumber(identityNumber).getData() == null) {
            result = true;
        }
        return result;
    }

    private Result validateCandidate(Candidate candidate) {

        if (!checkIfEmailExists(candidate.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }
        if (!checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
            return new ErrorResult("The entered ID number belongs to another account.");
        }
        if (!userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(), candidate.getLastName(), candidate.getDateOfBirth())) {
            return new ErrorResult("Please enter your information correctly.");
        }
        return null;
    }

}