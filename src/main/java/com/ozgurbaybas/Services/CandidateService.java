package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Candidate;

import java.util.List;

public interface CandidateService {
    Result add(Candidate candidate);
    Result update(Candidate candidate);
    Result delete(int id);
    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getById(int id);
    Result activate(String code);
    DataResult<Candidate> getByIdentityNumber(String identityNumber);
    DataResult<List<Candidate>> getAllByIsActivated(boolean isActivated);
}