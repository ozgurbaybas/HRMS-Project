package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;
import com.ozgurbaybas.Models.UserActivation;

import java.util.List;

public interface CandidateService {
    Result add(Candidate candidate);
    Result update(Candidate candidate);
    Result delete(Candidate candidate);
    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getById(int id);
    Result activate(UserActivation userActivation);
}