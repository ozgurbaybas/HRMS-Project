package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Level;
import com.ozgurbaybas.Repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Result add(Level level) {
        levelRepository.save(level);
        return new SuccessResult();
    }

    @Override
    public Result update(Level level) {
        levelRepository.save(level);
        return new SuccessResult();
    }

    @Override
    public Result delete(int id) {
        levelRepository.deleteById(id);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<Level>> getAll() {
        return new SuccessDataResult<List<Level>>(levelRepository.findAll());
    }

    @Override
    public DataResult<Level> getById(int id) {
        return new SuccessDataResult<Level>(levelRepository.getById(id));
    }

}