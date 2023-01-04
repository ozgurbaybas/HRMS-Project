package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Repository.UserActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        userActivation.setCode(generateCode());
        userActivation.setActivated(false);
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationRepository.save(userActivation);
        return emailService.sendEmail(userActivation.getUser());
    }

    @Override
    public Result update(UserActivation userActivation) {

        userActivationRepository.save(userActivation);
        return new SuccessResult();
    }

    private String generateCode() {

        UUID code = UUID.randomUUID();

        return code.toString();
    }

}