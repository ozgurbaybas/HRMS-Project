package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.FavoriteJobPosting;
import com.ozgurbaybas.Repository.FavoriteJobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
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
        favoriteJobPosting.setDateOfAddToFavorites(LocalDateTime.now());
        favoriteJobPostingRepository.save(favoriteJobPosting);
        return new SuccessResult();
    }

    @Override
    public Result update(FavoriteJobPosting favoriteJobPosting) {
        favoriteJobPostingRepository.save(favoriteJobPosting);
        return new SuccessResult();
    }

    @Override
    public Result delete(FavoriteJobPosting favoriteJobPosting) {
        favoriteJobPostingRepository.delete(favoriteJobPosting);
        return new SuccessResult();
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

    @Override
    public DataResult<List<FavoriteJobPosting>> getAllByCandidateIdSortedByDateOfAddToFavorites(int candidateId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "dateOfAddToFavorites");
        return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingRepository.getByCandidate_Id(candidateId, sort));
    }

}