package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.CoverLetter;

import java.util.List;

public interface CoverLetterService extends BaseEntityService<CoverLetter> {

    DataResult<List<CoverLetter>> getAllByCandidateId(int candidateId);

}