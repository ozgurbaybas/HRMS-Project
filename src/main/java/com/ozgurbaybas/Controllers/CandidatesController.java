package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {

    private CandidateService candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        candidateService = candidateService;
    }

    @PutMapping("/update")
    public Result update(@RequestBody Candidate candidate) {
        return candidateService.update(candidate);
    }

    @GetMapping("/getAll")
    public DataResult<List<Candidate>> getAll() {
        return candidateService.getAll();
    }

    @PutMapping("/activate")
    public Result activate(@RequestParam String code) { return candidateService.activate(code); }

    @GetMapping("getById")
    public DataResult<Candidate> getById(@RequestParam int id) {
        return candidateService.getById(id);
    }

    @GetMapping("/getAllByIsActivated")
    public DataResult<List<Candidate>> getAllByIsActivated(@RequestParam boolean isActivated) {
        return candidateService.getAllByIsActivated(isActivated);
    }
}