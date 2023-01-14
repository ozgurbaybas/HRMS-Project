package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.Education;
import com.ozgurbaybas.Repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private EducationRepository educationRepository;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public Result add(Education education) {

        educationRepository.save(education);
        return new SuccessResult("Education added.");
    }

    @Override
    public Result update(Education education) {

        educationRepository.save(education);
        return new SuccessResult("The tutorial has been updated.");
    }

    @Override
    public Result delete(Education education) {

        educationRepository.delete(education);
        return new SuccessResult("The tutorial has been deleted.");
    }

    @Override
    public DataResult<List<Education>> getAll() {
        return new SuccessDataResult<List<Education>>(educationRepository.findAll());
    }

    @Override
    public DataResult<Education> getById(int id) {
        return new SuccessDataResult<Education>(educationRepository.getById(id));
    }

    @Override
    public DataResult<List<Education>> getAllByResumeId(int resumeId) {
        return new SuccessDataResult<List<Education>>(educationRepository.getByResume_Id(resumeId));
    }


    @Override
    public DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(int resumeId) {

        Sort sort = Sort.by(Sort.Direction.DESC, "graduationDate");
        return new SuccessDataResult<List<Education>>(educationRepository.getByResume_Id(resumeId, sort));
    }

}