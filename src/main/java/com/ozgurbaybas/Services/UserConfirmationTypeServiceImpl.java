package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.UserConfirmationType;
import com.ozgurbaybas.Repository.UserConfirmationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConfirmationTypeServiceImpl implements UserConfirmationTypeService{

    private UserConfirmationTypeRepository userConfirmationTypeRepository;

    @Autowired
    public UserConfirmationTypeServiceImpl(UserConfirmationTypeRepository userConfirmationTypeRepository) {
        this.userConfirmationTypeRepository = userConfirmationTypeRepository;
    }

    @Override
    public Result add(UserConfirmationType userConfirmationType) {
        userConfirmationTypeRepository.save(userConfirmationType);
        return new SuccessResult("Approval type added.");
    }

    @Override
    public Result update(UserConfirmationType userConfirmationType) {
        userConfirmationTypeRepository.save(userConfirmationType);
        return new SuccessResult("Confirmation type updated.");
    }

    @Override
    public Result delete(int id) {
        userConfirmationTypeRepository.deleteById(id);
        return new SuccessResult("Confirmation type deleted.");
    }

    @Override
    public DataResult<List<UserConfirmationType>> getAll() {
        return new SuccessDataResult<List<UserConfirmationType>>(userConfirmationTypeRepository.findAll());
    }

    @Override
    public DataResult<UserConfirmationType> getById(int id) {
        return new SuccessDataResult<UserConfirmationType>(userConfirmationTypeRepository.getById(id));
    }

}