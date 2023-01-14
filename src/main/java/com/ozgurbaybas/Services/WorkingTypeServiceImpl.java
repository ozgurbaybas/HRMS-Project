package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.WorkingType;
import com.ozgurbaybas.Repository.WorkingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingTypeServiceImpl implements WorkingTypeService {

    private WorkingTypeRepository workingTypeRepository;

    @Autowired
    public WorkingTypeServiceImpl(WorkingTypeRepository workingTypeRepository) {
        this.workingTypeRepository = workingTypeRepository;
    }

    @Override
    public Result add(WorkingType workingType) {
        workingTypeRepository.save(workingType);
        return new SuccessResult();
    }

    @Override
    public Result update(WorkingType workingType) {
        workingTypeRepository.save(workingType);
        return new SuccessResult();
    }

    @Override
    public Result delete(WorkingType workingType) {
        workingTypeRepository.delete(workingType);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<WorkingType>> getAll() {
        return new SuccessDataResult<List<WorkingType>>(workingTypeRepository.findAll());
    }

    @Override
    public DataResult<WorkingType> getById(int id) {
        return new SuccessDataResult<WorkingType>(workingTypeRepository.getById(id));
    }

}