package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Skill;
import com.ozgurbaybas.Repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Result add(Skill skill) {
        skillRepository.save(skill);
        return new SuccessResult("Skill added.");
    }

    @Override
    public Result update(Skill skill) {
        skillRepository.save(skill);
        return new SuccessResult("Skill update.");
    }

    @Override
    public Result delete(Skill skill) {
        skillRepository.delete(skill);
        return new SuccessResult("Skill delete.");
    }

    @Override
    public DataResult<List<Skill>> getAll() {
        return new SuccessDataResult<List<Skill>>(skillRepository.findAll());
    }

    @Override
    public DataResult<Skill> getById(int id) {
        return new SuccessDataResult<Skill>(skillRepository.getById(id));
    }

}