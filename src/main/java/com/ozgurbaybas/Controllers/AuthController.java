package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.ErrorDataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registerCompanyStaff")
    public ResponseEntity<?> registerCompanyStaff(@Valid @RequestBody CompanyStaff companyStaff, String confirmPassword) {
        return ResponseEntity.ok(authService.resgisterCompanyStaff(companyStaff, confirmPassword));
    }

    @PostMapping("/registerCandidate")
    public ResponseEntity<?> registerCandidate(@Valid @RequestBody Candidate candidate, String confirmPassword) {
        return ResponseEntity.ok(authService.resgisterCandidate(candidate, confirmPassword));
    }

    @PostMapping("/registerEmployer")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody Employer employer, String confirmPassword) {
        return ResponseEntity.ok(authService.resgisterEmployer(employer, confirmPassword));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {

        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors);

        return errors;
    }

}