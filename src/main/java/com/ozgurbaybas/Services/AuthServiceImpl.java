package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.ErrorResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private CandidateService candidateService;
    private EmployerService employerService;

    @Autowired
    public AuthServiceImpl(UserService userService, CandidateService candidateService, EmployerService employerService) {
        this.userService = userService;
        this.candidateService = candidateService;
        this.employerService = employerService;
    }

    @Override
    public Result resgisterCandidate(Candidate candidate, String confirmPassword) {

        if (!checkIfEmailExists(candidate.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }

        if (!checkIfPasswordsMatch(candidate.getPassword(), confirmPassword)) {
            return new ErrorResult("Password matching did not occur. Please check and try again.");
        }

        return candidateService.add(candidate);
    }

    @Override
    public Result resgisterEmployer(Employer employer, String confirmPassword) {

        if (!checkIfEmailExists(employer.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }

        if (!checkIfPasswordsMatch(employer.getPassword(), confirmPassword)) {
            return new ErrorResult("Password matching did not occur. Please check and try again.");
        }

        return employerService.add(employer);
    }

    private boolean checkIfEmailExists(String email) {

        boolean result = false;

        if (userService.getByEmail(email).getData() == null) {
            result = true;
        }
        return result;
    }

    private boolean checkIfPasswordsMatch(String password, String confirmPassword) {

        boolean result = false;

        if (password.equals(confirmPassword)) {
            result = true;
        }
        return result;
    }
}