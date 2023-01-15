package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobPostingConfirmationType;
import com.ozgurbaybas.Services.JobPostingConfirmationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPostingConfirmationTypes")
@CrossOrigin
public class JobPostingConfirmationTypesController {

    private JobPostingConfirmationTypeService jobPostingConfirmationTypeService;

    @Autowired
    public JobPostingConfirmationTypesController(JobPostingConfirmationTypeService jobPostingConfirmationTypeService) {
        this.jobPostingConfirmationTypeService = jobPostingConfirmationTypeService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPostingConfirmationType jobPostingConfirmationType) {
        return jobPostingConfirmationTypeService.add(jobPostingConfirmationType);
    }

    @PutMapping("/update")
    public Result update(@RequestBody JobPostingConfirmationType jobPostingConfirmationType) {
        return jobPostingConfirmationTypeService.update(jobPostingConfirmationType);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobPostingConfirmationType>> getAll() {
        return jobPostingConfirmationTypeService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<JobPostingConfirmationType> getById(@RequestParam int id) {
        return jobPostingConfirmationTypeService.getById(id);
    }

}