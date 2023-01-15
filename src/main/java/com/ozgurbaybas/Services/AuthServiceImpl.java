package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Entities.User;
import com.ozgurbaybas.Core.Utilities.Result.ErrorResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private CompanyStaffService companyStaffService;
    private CandidateService candidateService;
    private EmployerService employerService;

    @Autowired
    public AuthServiceImpl(CompanyStaffService companyStaffService, CandidateService candidateService, EmployerService employerService) {
        this.companyStaffService = companyStaffService;
        this.candidateService = candidateService;
        this.employerService = employerService;
    }

    @Override
    public Result resgisterCompanyStaff(CompanyStaff user, String confirmPassword) {

        validateUser(user, confirmPassword);
        return companyStaffService.add(user);
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
            return new ErrorResult("Password matching did not occur. Please check and try again.");
        }
        return null;
    }
}