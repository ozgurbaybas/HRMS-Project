package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

    private CandidateService candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Candidate>> getAll() {
        return this.candidateService.getAll();
    }
}