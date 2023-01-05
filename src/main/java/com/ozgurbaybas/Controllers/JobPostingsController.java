package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Services.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostings")
public class JobPostingsController {

    private JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPosting jobPosting) {
        return jobPostingService.add(jobPosting);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody JobPosting jobPosting) {
        return jobPostingService.delete(jobPosting);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPosting>> getAll() {
        return jobPostingService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<JobPosting> getById(@RequestParam int id) {
        return jobPostingService.getById(id);
    }

    @GetMapping("/getAllActiveJobPostingDetails")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails() {
        return jobPostingService.getAllActiveJobPostingDetails();
    }

    /*
    @GetMapping("/getAllActiveJobPostingDetailsSortedByPostingDate")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate() {
        return jobPostingService.getAllActiveJobPostingDetailsSortedByPostingDate();
    }
     */

    @GetMapping("/getAllActiveJobPostingDetailsByCompanyName")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName) {
        return jobPostingService.getAllActiveJobPostingDetailsByCompanyName(companyName);
    }

    @PostMapping("/doActiveOrPassive")
    public Result doActiveOrPassive(int id, boolean isActive) {
        return jobPostingService.doActiveOrPassive(id, isActive);
    }
}