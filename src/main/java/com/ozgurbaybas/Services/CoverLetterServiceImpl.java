package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.CoverLetter;
import com.ozgurbaybas.Repository.CoverLetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    private CoverLetterRepository coverLetterRepository;

    @Autowired
    public CoverLetterServiceImpl(CoverLetterRepository coverLetterRepository) {
        this.coverLetterRepository = coverLetterRepository;
    }

    @Override
    public Result add(CoverLetter coverLetter) {

        coverLetterRepository.save(coverLetter);
        return new SuccessResult("Added cover letter.");
    }

    @Override
    public Result update(CoverLetter coverLetter) {

        coverLetterRepository.save(coverLetter);
        return new SuccessResult("The cover letter has been updated.");
    }

    @Override
    public Result delete(CoverLetter coverLetter) {

        coverLetterRepository.delete(coverLetter);
        return new SuccessResult("The cover letter has been deleted.");
    }

    @Override
    public DataResult<List<CoverLetter>> getAll() {
        return new SuccessDataResult<List<CoverLetter>>(coverLetterRepository.findAll());
    }

    @Override
    public DataResult<CoverLetter> getById(int id) {
        return new SuccessDataResult<CoverLetter>(coverLetterRepository.getById(id));
    }

}