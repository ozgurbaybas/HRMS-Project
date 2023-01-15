package com.ozgurbaybas.Services;

import org.springframework.data.domain.Sort;
import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Language;
import com.ozgurbaybas.Repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Result add(Language language) {

        languageRepository.save(language);
        return new SuccessResult("Language added.");
    }

    @Override
    public Result update(Language language) {

        languageRepository.save(language);
        return new SuccessResult("Language updated.");
    }

    @Override
    public Result delete(int id) {

        this.languageRepository.deleteById(id);
        return new SuccessResult("Language deleted.");
    }

    @Override
    public DataResult<List<Language>> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "language");
        return new SuccessDataResult<List<Language>>(languageRepository.findAll(sort));
    }

    @Override
    public DataResult<Language> getById(int id) {
        return new SuccessDataResult<Language>(languageRepository.getById(id));
    }

}