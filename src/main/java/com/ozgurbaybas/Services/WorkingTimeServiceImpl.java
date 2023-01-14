package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.WorkingTime;
import com.ozgurbaybas.Repository.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingTimeServiceImpl implements WorkingTimeService {

    private WorkingTimeRepository workingTimeRepository;

    @Autowired
    public WorkingTimeServiceImpl(WorkingTimeRepository workingTimeRepository) {
        this.workingTimeRepository = workingTimeRepository;
    }

    @Override
    public Result add(WorkingTime workingTime) {
        workingTimeRepository.save(workingTime);
        return new SuccessResult();
    }

    @Override
    public Result update(WorkingTime workingTime) {
        workingTimeRepository.save(workingTime);
        return new SuccessResult();
    }

    @Override
    public Result delete(WorkingTime workingTime) {
        workingTimeRepository.delete(workingTime);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<WorkingTime>> getAll() {
        return new SuccessDataResult<List<WorkingTime>>(workingTimeRepository.findAll());
    }

    @Override
    public DataResult<WorkingTime> getById(int id) {
        return new SuccessDataResult<WorkingTime>(workingTimeRepository.getById(id));
    }

}