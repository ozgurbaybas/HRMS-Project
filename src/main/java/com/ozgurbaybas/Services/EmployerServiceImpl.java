package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private UserActivationService userActivationService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository,	UserActivationService userActivationService) {
        this.employerRepository = employerRepository;
        this.userActivationService = userActivationService;
    }

    @Override
    public Result add(Employer employer) {

        if (!checkIfDomainsMatch(employer.getWebAddress(), employer.getEmail())) {
            return new ErrorResult("Web adresi ile e-posta aynı alan adına sahip olmalıdır.");
        }

        employerRepository.save(employer);
        return userActivationService.add(new UserActivation(employer));
    }

    @Override
    public Result update(Employer employer) {
        employerRepository.save(employer);
        return new SuccessResult();
    }

    @Override
    public Result delete(Employer employer) {
        employerRepository.delete(employer);
        return new SuccessResult();
    }


    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(employerRepository.findAll());
    }

    @Override
    public DataResult<Employer> getById(int id) {
        return new SuccessDataResult<Employer>(employerRepository.getById(id));
    }

    @Override
    public Result activate(UserActivation userActivation) {

        userActivation.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationService.update(userActivation);
        return new SuccessResult("Your membership is in the approval phase.");
    }

    private boolean checkIfDomainsMatch(String webAddress, String email) {

        String[] splitEmailArray = email.split("@");

        return webAddress.contains(splitEmailArray[1]);
    }
}