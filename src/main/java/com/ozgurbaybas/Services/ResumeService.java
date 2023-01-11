package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.DTO.ResumeWithAllRelatedEntitiesDto;
import com.ozgurbaybas.Models.Resume;

public interface ResumeService extends BaseEntityService<Resume> {
    Result addCoverLetterToResume(int resumeId, int coverLetterId);
    DataResult<Resume> getByCandidateId(int candidateId);
    DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId);
}