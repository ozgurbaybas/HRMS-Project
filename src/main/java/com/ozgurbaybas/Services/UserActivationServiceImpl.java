package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Repository.UserActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private UserActivationRepository userActivationRepository;
    private EmailService emailService;

    @Autowired
    public UserActivationServiceImpl(UserActivationRepository userActivationRepository, EmailService emailService) {
        this.userActivationRepository = userActivationRepository;
        this.emailService = emailService;
    }
    @Override
    public Result add(UserActivation userActivation) {

        userActivation.setActivated(false);
        userActivation.setCode(generateCode());
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationRepository.save(userActivation);
        return emailService.sendEmail(userActivation.getUser());
    }
    @Override
    public Result update(UserActivation userActivation) {
        userActivationRepository.save(userActivation);
        return new SuccessResult();
    }
    @Override
    public Result delete(int id) {
        userActivationRepository.deleteById(id);
        return new SuccessResult();
    }
    @Override
    public DataResult<List<UserActivation>> getAll() {
        return new SuccessDataResult<List<UserActivation>>(userActivationRepository.findAll());
    }
    @Override
    public DataResult<UserActivation> getById(int id) {
        return new SuccessDataResult<UserActivation>(userActivationRepository.getById(id));
    }
    @Override
    public DataResult<UserActivation> getByCode(String code) {
        return new SuccessDataResult<UserActivation>(userActivationRepository.getByCode(code));
    }
    @Override
    public DataResult<UserActivation> getByUserId(int userId) {
        return new SuccessDataResult<UserActivation>(userActivationRepository.getByUser_Id(userId));
    }

    @Override
    public DataResult<List<UserActivation>> getAllByIsActivated(boolean isActivated) {
        return new SuccessDataResult<List<UserActivation>>(userActivationRepository.getByIsActivated(isActivated));
    }

    private String generateCode() {
        UUID code = UUID.randomUUID();
        return code.toString();
    }
}