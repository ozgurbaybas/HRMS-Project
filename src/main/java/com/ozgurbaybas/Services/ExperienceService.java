package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.Experience;

import java.util.List;

public interface ExperienceService extends BaseEntityService<Experience> {

    DataResult<List<Experience>> getAllByResumeId(int resumeId);
    DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId);

}