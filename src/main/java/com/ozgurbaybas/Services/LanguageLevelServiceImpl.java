package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.LanguageLevel;
import com.ozgurbaybas.Repository.LanguageLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageLevelServiceImpl implements LanguageLevelService {

    private LanguageLevelRepository languageLevelRepository;

    @Autowired
    public LanguageLevelServiceImpl(LanguageLevelRepository languageLevelRepository) {
        this.languageLevelRepository = languageLevelRepository;
    }

    @Override
    public Result add(LanguageLevel languageLevel) {
        languageLevelRepository.save(languageLevel);
        return new SuccessResult("Language level added.");
    }

    @Override
    public Result update(LanguageLevel languageLevel) {
        languageLevelRepository.save(languageLevel);
        return new SuccessResult("Language level updated.");
    }

    @Override
    public Result delete(LanguageLevel languageLevel) {
        languageLevelRepository.delete(languageLevel);
        return new SuccessResult("Language level deleted.");
    }

    @Override
    public DataResult<List<LanguageLevel>> getAll() {
        return new SuccessDataResult<List<LanguageLevel>>(languageLevelRepository.findAll());
    }

    @Override
    public DataResult<LanguageLevel> getById(int id) {
        return new SuccessDataResult<LanguageLevel>(languageLevelRepository.getById(id));
    }

}