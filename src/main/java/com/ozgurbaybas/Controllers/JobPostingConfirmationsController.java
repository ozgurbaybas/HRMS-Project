package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPostingConfirmation;
import com.ozgurbaybas.Services.JobPostingConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostingConfirmations")
@CrossOrigin
public class JobPostingConfirmationsController {

    private JobPostingConfirmationService jobPostingConfirmationService;

    @Autowired
    public JobPostingConfirmationsController(JobPostingConfirmationService jobPostingConfirmationService) {
        this.jobPostingConfirmationService = jobPostingConfirmationService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
        return jobPostingConfirmationService.add(jobPostingConfirmation);
    }

    @PostMapping("/update")
    public Result update(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
        return jobPostingConfirmationService.update(jobPostingConfirmation);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
        return jobPostingConfirmationService.delete(jobPostingConfirmation);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPostingConfirmation>> getAll() {
        return jobPostingConfirmationService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<JobPostingConfirmation> getById(@RequestParam int id) {
        return jobPostingConfirmationService.getById(id);
    }

}