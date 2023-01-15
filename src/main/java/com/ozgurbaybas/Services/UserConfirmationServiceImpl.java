package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.UserConfirmation;
import com.ozgurbaybas.Repository.UserConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserConfirmationServiceImpl implements UserConfirmationService {

    private UserConfirmationRepository userConfirmationRepository;
    private EmailService emailService;

    @Autowired
    public UserConfirmationServiceImpl(UserConfirmationRepository userConfirmationRepository, EmailService emailService) {
        this.userConfirmationRepository = userConfirmationRepository;
        this.emailService = emailService;
    }

    @Override
    public Result add(UserConfirmation userConfirmation) {
        userConfirmation.setIsConfirmedDate(LocalDate.now());
        userConfirmationRepository.save(userConfirmation);
        emailService.sendEmail(userConfirmation.getUser());
        return new SuccessResult();
    }

    @Override
    public Result update(UserConfirmation userConfirmation) {

        userConfirmationRepository.save(userConfirmation);
        return new SuccessResult();
    }

    @Override
    public Result delete(int id) {

        userConfirmationRepository.deleteById(id);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<UserConfirmation>> getAll() {
        return new SuccessDataResult<List<UserConfirmation>>(userConfirmationRepository.findAll());
    }

    @Override
    public DataResult<UserConfirmation> getById(int id) {
        return new SuccessDataResult<UserConfirmation>(userConfirmationRepository.getById(id));
    }

    @Override
    public DataResult<List<UserConfirmation>> getAllByUserId(int userId) {
        return new SuccessDataResult<List<UserConfirmation>>(userConfirmationRepository.getByUser_Id(userId));
    }
}