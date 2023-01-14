package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.CoverLetter;
import com.ozgurbaybas.Services.CoverLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coverLetters")
@CrossOrigin
public class CoverLettersController {

    private CoverLetterService coverLetterService;

    @Autowired
    public CoverLettersController(CoverLetterService coverLetterService) {
        this.coverLetterService = coverLetterService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CoverLetter coverLetter) {
        return coverLetterService.add(coverLetter);
    }

    @PostMapping("/PutMapping")
    public Result update(@RequestBody CoverLetter coverLetter) {
        return coverLetterService.update(coverLetter);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody CoverLetter coverLetter) {
        return coverLetterService.delete(coverLetter);
    }

    @GetMapping("/getAll")
    public DataResult<List<CoverLetter>> getAll() {
        return coverLetterService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<CoverLetter> getById(@RequestParam int id) {
        return coverLetterService.getById(id);
    }

    @GetMapping("/getAllByCandidateId")
    public DataResult<List<CoverLetter>> getAllByCandidateId(@RequestParam int candidateId) {
        return coverLetterService.getAllByCandidateId(candidateId);
    }

}