package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getById")
    public DataResult<Employer> getById(@RequestParam int id) {
        return employerService.getById(id);
    }

    @GetMapping("/getByIsActivatedAndIsConfirmed")
    public DataResult<List<Employer>> getAllByIsActivatedAndIsConfirmed(@RequestParam boolean isActivated, @RequestParam boolean isConfirmed) {
        return employerService.getAllByIsActivatedAndIsConfirmed(isActivated, isConfirmed);
    }

    @PostMapping("/activate")
    public Result activate(@RequestParam String code) { return employerService.activate(code);}

    @PostMapping("/confirm")
    public Result confirm(@RequestParam int employerId, @RequestParam int companyStaffId, @RequestParam boolean isConfirmed) {
        return employerService.confirm(employerId, companyStaffId, isConfirmed);
    }
}