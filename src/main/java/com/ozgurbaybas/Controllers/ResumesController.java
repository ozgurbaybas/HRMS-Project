package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.DTO.ResumeWithAllRelatedEntitiesDto;
import com.ozgurbaybas.Models.Resume;
import com.ozgurbaybas.Services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {

    private ResumeService resumeService;

    @Autowired
    public ResumesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Resume resume) {
        return resumeService.add(resume);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Resume resume) {
        return resumeService.update(resume);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Resume resume) {
        return resumeService.delete(resume);
    }

    @GetMapping("/getAll")
    public DataResult<List<Resume>> getAll() {
        return resumeService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Resume> getById(@RequestParam int id) {
        return resumeService.getById(id);
    }

    @PostMapping("/addCoverLetterToResume")
    public Result addCoverLetterToResume(int resumeId, int coverLetterId) {
        return resumeService.addCoverLetterToResume(resumeId, coverLetterId);
    }
    @GetMapping("/getResumeDetailsByCandidateId")
    public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId) {
        return resumeService.getResumeDetailsByCandidateId(candidateId);
    }

}