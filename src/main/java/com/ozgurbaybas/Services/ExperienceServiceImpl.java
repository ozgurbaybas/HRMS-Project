package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Experience;
import com.ozgurbaybas.Repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public Result add(Experience experience) {
        experienceRepository.save(experience);
        return new SuccessResult("Work experience added.");
    }

    @Override
    public Result update(Experience experience) {
        experienceRepository.save(experience);
        return new SuccessResult("Work experience updated.");

    }

    @Override
    public Result delete(Experience experience) {
        experienceRepository.delete(experience);
        return new SuccessResult("Work experience deleted.");
    }

    @Override
    public DataResult<List<Experience>> getAll() {
        return new SuccessDataResult<List<Experience>>(experienceRepository.findAll());
    }

    @Override
    public DataResult<Experience> getById(int id) {
        return new SuccessDataResult<Experience>(experienceRepository.getById(id));
    }

    @Override
    public DataResult<List<Experience>> getAllByResumeId(int resumeId) {
        return new SuccessDataResult<List<Experience>>(experienceRepository.getByResume_Id(resumeId));
    }

    /*
    @Override
    public DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "terminationDate");
        return new SuccessDataResult<List<Experience>>(experienceRepository.getByResume_Id(resumeId, sort));
    }
     */
}