package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.JobTitle;
import com.ozgurbaybas.Services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

    private JobTitleService jobTitleService;

    @Autowired
    public JobTitlesController(JobTitleService jobTitleService) {
        super();
        this.jobTitleService = jobTitleService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobTitle jobTitle) {
        return this.jobTitleService.add(jobTitle);
    }

    @PostMapping("/update")
    public Result update(@RequestBody JobTitle jobTitle) {
        return this.jobTitleService.update(jobTitle);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody JobTitle jobTitle) {
        return this.jobTitleService.delete(jobTitle);
    }

    @GetMapping("/getAll")
    public DataResult<List<JobTitle>> getAll() {
        return this.jobTitleService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<JobTitle> getById(@RequestParam Long id) {
        return this.jobTitleService.getById(id);
    }
}