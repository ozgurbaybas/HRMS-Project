package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.Education;

import java.util.List;

public interface EducationService extends BaseEntityService<Education> {

    DataResult<List<Education>> getAllByResumeId(int resumeId);
    DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(int resumeId);

}
