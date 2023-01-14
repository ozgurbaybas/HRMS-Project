package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Services.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostings")
@CrossOrigin
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
    @PostMapping("/confirm")
    public Result confirm(@RequestParam int jobPostingId, @RequestParam int companyStaffId,	@RequestParam boolean isConfirmed) {
        return jobPostingService.confirm(jobPostingId, companyStaffId, isConfirmed);
    }
    @PostMapping("/doActiveOrPassive")
    public Result doActiveOrPassive(@RequestParam int id, @RequestParam boolean isActive) {
        return jobPostingService.doActiveOrPassive(id, isActive);
    }

    @GetMapping("/getAllActiveOnes")
    public DataResult<List<JobPosting>> getAllActiveOnes() {
        return jobPostingService.getAllActiveOnes();
    }

    @GetMapping("/getAllActiveOnesByPage")
    public DataResult<List<JobPosting>> getAllActiveOnesByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        return jobPostingService.getAllActiveOnesByPage(pageNo, pageSize);
    }

    @GetMapping("/getAllActiveOnesSortedByPostingDate")
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate() {
        return jobPostingService.getAllActiveOnesSortedByPostingDate();
    }

    @GetMapping("/getAllActiveOnesByPageSortedByPostingDate")
    public DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(@RequestParam int pageNo,	@RequestParam int pageSize) {
        return jobPostingService.getAllActiveOnesByPageSortedByPostingDate(pageNo, pageSize);
    }

    @GetMapping("/getAllActiveOnesSortedByPostingDateTop6")
    public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6() {
        return jobPostingService.getAllActiveOnesSortedByPostingDateTop6();
    }
    @GetMapping("/getAllActiveOnesByEmployerId")
    public DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(@RequestParam int employerId) {
        return jobPostingService.getAllActiveOnesByEmployerId(employerId);
    }

    @GetMapping("/getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity")
    public DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(@RequestParam int workingTimeId,
                                                                                                   @RequestParam int workingTypeId, @RequestParam int cityId) {
        return jobPostingService.getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(workingTimeId, workingTypeId, cityId);
    }

    @GetMapping("/getAllActiveOnesByPageFilteredByWorkingTimeAndWorkingTypeAndCity")
    public DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByWorkingTimeAndWorkingTypeAndCity(@RequestParam int workingTimeId,
                                                                                                         @RequestParam int workingTypeId, @RequestParam int cityId, @RequestParam int pageNo, @RequestParam int pageSize) {
        return jobPostingService.getAllActiveOnesByPageFilteredByWorkingTimeAndWorkingTypeAndCity(workingTimeId, workingTypeId, cityId, pageNo, pageSize);
    }

}