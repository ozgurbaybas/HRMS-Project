package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerCandidate")
    public Result registerCandidate(@RequestBody Candidate candidate, String confirmPassword) {
        return this.authService.resgisterCandidate(candidate, confirmPassword);
    }

    @PostMapping("/registerEmployer")
    public Result registerEmployer(@RequestBody Employer employer, String confirmPassword) {
        return this.authService.resgisterEmployer(employer, confirmPassword);
    }
}