package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.ErrorResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private CandidateService candidateService;
    private EmployerService employerService;

    @Autowired
    public AuthManager(CandidateService candidateService, EmployerService employerService) {
        this.candidateService = candidateService;
        this.employerService = employerService;
    }

    @Override
    public Result resgisterCandidate(Candidate user, String confirmPassword) {

        validateUser(user, confirmPassword);

        return candidateService.add(user);
    }

    @Override
    public Result resgisterEmployer(Employer user, String confirmPassword) {

        validateUser(user, confirmPassword);

        return employerService.add(user);
    }

    private boolean checkIfPasswordsMatch(String password, String confirmPassword) {

        boolean result = false;

        if (password.equals(confirmPassword)) {
            result = true;
        }

        return result;
    }

    private Result validateUser(User user, String confirmPassword) {

        if (!checkIfPasswordsMatch(user.getPassword(), confirmPassword)) {
            return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
        }

        return null;
    }

}