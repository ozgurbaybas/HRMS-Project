package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.ErrorResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (!checkIfEmailIsValid(candidate.getEmail())) {
            return new ErrorResult("Please enter a valid e-mail address.");
        }

        if (checkIfEmailExists(candidate.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }

        if (!checkIfPasswordsMatch(candidate.getPassword(), confirmPassword)) {
            return new ErrorResult("Password matching did not occur. Please check and try again.");
        }

        return candidateService.add(candidate);
    }

    @Override
    public Result resgisterEmployer(Employer employer, String confirmPassword) {

        if (!checkIfEmailIsValid(employer.getEmail())) {
            return new ErrorResult("Please enter a valid e-mail address.");
        }

        if (checkIfEmailExists(employer.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }

        if (!checkIfPasswordsMatch(employer.getPassword(), confirmPassword)) {
            return new ErrorResult("Password matching did not occur. Please check and try again.");
        }

        return employerService.add(employer);
    }

    private boolean checkIfEmailIsValid(String email) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean checkIfEmailExists(String email) {

        boolean result = false;

        for (User user : userService.getAll().getData()) {
            if (user.getEmail() == email) {
                result = true;
            }
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