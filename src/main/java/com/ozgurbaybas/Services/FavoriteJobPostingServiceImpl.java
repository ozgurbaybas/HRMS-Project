package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.FavoriteJobPosting;
import com.ozgurbaybas.Models.JobPosting;
import com.ozgurbaybas.Repository.FavoriteJobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteJobPostingServiceImpl implements FavoriteJobPostingService {

    private FavoriteJobPostingRepository favoriteJobPostingRepository;

    @Autowired
    public FavoriteJobPostingServiceImpl(FavoriteJobPostingRepository favoriteJobPostingRepository) {
        this.favoriteJobPostingRepository = favoriteJobPostingRepository;
    }

    @Override
    public Result add(FavoriteJobPosting favoriteJobPosting) {
        if (getByCandidateIdAndJobPostingId(favoriteJobPosting.getCandidate().getId(), favoriteJobPosting.getJobPosting().getId()).getData() == null) {
            favoriteJobPosting.setDateOfAddToFavorites(LocalDateTime.now());
            favoriteJobPostingRepository.save(favoriteJobPosting);

            return new SuccessResult("The ad has been added to favourites.");
        }

        return new ErrorResult();
    }

    @Override
    public Result update(FavoriteJobPosting favoriteJobPosting) {
        favoriteJobPostingRepository.save(favoriteJobPosting);
        return new SuccessResult();
    }

    @Override
    public Result delete(int id) {
        favoriteJobPostingRepository.deleteById(id);
        return new SuccessResult("Ad removed from favourites.");
    }

    @Override
    public DataResult<List<FavoriteJobPosting>> getAll() {
        return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingRepository.findAll());
    }

    @Override
    public DataResult<FavoriteJobPosting> getById(int id) {
        return new SuccessDataResult<FavoriteJobPosting>(favoriteJobPostingRepository.getById(id));
    }

    @Override
    public DataResult<List<FavoriteJobPosting>> getAllByCandidateId(int candidateId) {
        return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingRepository.getByCandidate_Id(candidateId));
    }

    public DataResult<List<JobPosting>> getAllActiveJobPostingsByCandidateIdSortedByDateOfAddToFavorites(int candidateId) {

        List<JobPosting> jobPostings = new ArrayList<JobPosting>();

        Sort sort = Sort.by(Sort.Direction.DESC, "dateOfAddToFavorites");

        for (FavoriteJobPosting favoriteJobPosting : favoriteJobPostingRepository.getByCandidate_Id(candidateId, sort)) {
            if (favoriteJobPosting.getJobPosting().isActive()) {
                jobPostings.add(favoriteJobPosting.getJobPosting());
            } else {
                delete(favoriteJobPosting.getId());
            }
        }

        return new SuccessDataResult<List<JobPosting>>(jobPostings);
    }

    @Override
    public DataResult<FavoriteJobPosting> getByCandidateIdAndJobPostingId(int candidateId, int jobPostingId) {
        return new SuccessDataResult<FavoriteJobPosting>(favoriteJobPostingRepository.getByCandidate_IdAndJobPosting_Id(candidateId, jobPostingId));
    }

}