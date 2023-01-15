package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.FavoriteJobPosting;
import com.ozgurbaybas.Models.JobPosting;

import java.util.List;

public interface FavoriteJobPostingService extends BaseEntityService<FavoriteJobPosting> {

    DataResult<List<FavoriteJobPosting>> getAllByCandidateId(int candidateId);
    DataResult<List<JobPosting>> getAllActiveJobPostingsByCandidateIdSortedByDateOfAddToFavorites(int candidateId);
    DataResult<FavoriteJobPosting> getByCandidateIdAndJobPostingId(int candidateId, int jobPostingId);

}