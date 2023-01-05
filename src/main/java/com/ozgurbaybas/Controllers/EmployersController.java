package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        employerService = employerService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Employer>> getAll() {
        return employerService.getAll();
    }

    @GetMapping("/getByIsActivatedAndIsConfirmed")
    public DataResult<List<Employer>> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed) {
        return employerService.getByIsActivatedAndIsConfirmed(isActivated, isConfirmed);
    }

    @PostMapping("/activate")
    public Result activate(String code) { return employerService.activate(code);}

    @PostMapping("/confirm")
    public Result confirm(Integer employerId, Integer companyStaffId, boolean isConfirmed) {
        return employerService.confirm(employerId, companyStaffId, isConfirmed);
    }
}