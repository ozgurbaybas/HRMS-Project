package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.CoverLetter;

public interface CoverLetterService extends BaseEntityService<CoverLetter> {

    DataResult<CoverLetter> getByCandidateId(int candidateId);

}