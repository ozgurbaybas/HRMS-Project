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
@CrossOrigin
public class EmployersController {

    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        employerService = employerService;
    }

    @PutMapping("/update")
    public Result update(@RequestBody Employer employer) {
        return employerService.update(employer);
    }

    @GetMapping("/getAll")
    public DataResult<List<Employer>> getAll() {
        return employerService.getAll();
    }

    @GetMapping("getById")
    public DataResult<Employer> getById(@RequestParam int id) {
        return employerService.getById(id);
    }

    @PutMapping("/activate")
    public Result activate(@RequestParam String code) { return employerService.activate(code);}

    public Result confirm(@RequestParam int employerId, @RequestParam int companyStaffId, @RequestParam int userConfirmationTypeId, @RequestParam boolean isConfirmed) {
        return employerService.confirm(employerId, companyStaffId, userConfirmationTypeId, isConfirmed);
    }

    @GetMapping("/getAllOnesThatWaitingForAccountConfirmation")
    public DataResult<List<Employer>> getAllOnesThatWaitingForAccountConfirmation() {
        return employerService.getAllOnesThatWaitingForAccountConfirmation();
    }

    @GetMapping("/getAllOnesThatWaitingForUpdateConfirmation")
    public DataResult<List<Employer>> getAllOnesThatWaitingForUpdateConfirmation() {
        return employerService.getAllOnesThatWaitingForUpdateConfirmation();
    }

    @GetMapping("/getAllByIsActivated")
    public DataResult<List<Employer>> getAllByIsActivated(@RequestParam boolean isActivated) {
        return employerService.getAllByIsActivated(isActivated);
    }

    @GetMapping("/getAllByIsConfirmedAndUserConfirmationTypeIdSortedByCompanyName")
    public DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeIdSortedByCompanyName(@RequestParam boolean isConfirmed, @RequestParam int userConfirmationTypeId) {
        return employerService.getAllByIsConfirmedAndUserConfirmationTypeIdSortedByCompanyName(isConfirmed, userConfirmationTypeId);
    }

    @GetMapping("/getOneThatWaitingForUpdateConfirmationById")
    public DataResult<Employer> getOneThatWaitingForUpdateConfirmationById(@RequestParam int id) {
        return employerService.getOneThatWaitingForUpdateConfirmationById(id);
    }
}