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

    @PostMapping("/update")
    public Result update(@RequestBody JobPosting jobPosting) {
        return jobPostingService.update(jobPosting);
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

    @GetMapping("/getActiveJobPostingDetails")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetails() {
        return jobPostingService.getActiveJobPostingDetails();
    }

    /*
    @GetMapping("/getActiveJobPostingDetailsSortedByPostingDate")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsSortedByPostingDate() {
        return jobPostingService.getActiveJobPostingDetailsSortedByPostingDate();
    }
     */

    @GetMapping("/getActiveJobPostingDetailsByEmployerId")
    public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsByEmployerId(int employerId) {
        return jobPostingService.getActiveJobPostingDetailsByEmployerId(employerId);
    }

}