package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.Employer;

public interface AuthService {

    Result resgisterCandidate(Candidate candidate, String confirmPassword);
    Result resgisterEmployer(Employer employer, String confirmPassword);
    Result resgisterCompanyStaff(CompanyStaff companyStaff, String confirmPassword);

}