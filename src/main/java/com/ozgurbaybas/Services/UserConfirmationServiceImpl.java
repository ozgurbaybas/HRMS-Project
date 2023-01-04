package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.ErrorResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.UserConfirmation;
import com.ozgurbaybas.Repository.UserConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

        if (userConfirmation.isConfirmed() == false) {
            return new ErrorResult("Membership not confirmed.");
        }
        return new SuccessResult("Membership confirmed");
    }
}