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
@CrossOrigin
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

    @PutMapping("/update")
    public Result update(@RequestBody Resume resume) {
        return resumeService.update(resume);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam int id) {
        return resumeService.delete(id);
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
    public Result addCoverLetterToResume(@RequestParam int resumeId, @RequestParam int coverLetterId) {
        return resumeService.addCoverLetterToResume(resumeId, coverLetterId);
    }

    @GetMapping("/getAllResumesDetailsByActivatedCandidate")
    public DataResult<List<ResumeWithAllRelatedEntitiesDto>> getAllResumesDetailsByActivatedCandidate() {
        return resumeService.getAllResumesDetailsByActivatedCandidate();
    }

    @GetMapping("/getResumeDetailsByCandidateId")
    public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(@RequestParam int candidateId) {
        return resumeService.getResumeDetailsByCandidateId(candidateId);
    }

    @DeleteMapping("/deleteCoverLetterFromResume")
    public Result deleteCoverLetterFromResume(@RequestParam int resumeId) {
        return resumeService.deleteCoverLetterFromResume(resumeId);
    }

    @GetMapping("/getByCandidateId")
    public DataResult<Resume> getByCandidateId(@RequestParam int candidateId) {
        return resumeService.getByCandidateId(candidateId);
    }

}