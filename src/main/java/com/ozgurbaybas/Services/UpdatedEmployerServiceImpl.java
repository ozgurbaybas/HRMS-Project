package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.UpdatedEmployer;
import com.ozgurbaybas.Repository.UpdatedEmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdatedEmployerServiceImpl implements UpdatedEmployerService {

    private UpdatedEmployerRepository updatedEmployerRepository;

    @Autowired
    public UpdatedEmployerServiceImpl(UpdatedEmployerRepository updatedEmployerRepository) {
        this.updatedEmployerRepository = updatedEmployerRepository;
    }

    @Override
    public Result add(UpdatedEmployer updatedEmployer) {

        updatedEmployerRepository.save(updatedEmployer);
        return new SuccessResult("Added updated employer.");
    }

    @Override
    public Result update(UpdatedEmployer updatedEmployer) {

        updatedEmployerRepository.save(updatedEmployer);
        return new SuccessResult("Updated employer updated.");
    }

    @Override
    public Result delete(int id) {

        updatedEmployerRepository.deleteById(id);
        return new SuccessResult("The updated employer has been deleted.");
    }

    @Override
    public DataResult<List<UpdatedEmployer>> getAll() {
        return new SuccessDataResult<List<UpdatedEmployer>>(updatedEmployerRepository.findAll());
    }

    @Override
    public DataResult<UpdatedEmployer> getById(int id) {
        return new SuccessDataResult<UpdatedEmployer>(updatedEmployerRepository.getById(id));
    }

    @Override
    public DataResult<UpdatedEmployer> getByEmployerId(int employerId) {
        return new SuccessDataResult<UpdatedEmployer>(updatedEmployerRepository.getByEmployer_Id(employerId));
    }

}