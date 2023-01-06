package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Education;
import com.ozgurbaybas.Services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {

    private EducationService educationService;

    @Autowired
    public EducationsController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Education education) {
        return educationService.add(education);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Education education) {
        return educationService.update(education);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Education education) {
        return educationService.delete(education);
    }

    @GetMapping("/getAll")
    public DataResult<List<Education>> getAll() {
        return educationService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Education> getById(@RequestParam int id) {
        return educationService.getById(id);
    }

    /*
    @GetMapping("/getAllByResumeIdSortedByGraduationDate")
    public DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(@RequestParam int resumeId) {
        return educationService.getAllByResumeIdSortedByGraduationDate(resumeId);
    }
     */

}