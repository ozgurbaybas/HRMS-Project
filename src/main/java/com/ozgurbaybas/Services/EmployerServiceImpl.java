package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Models.Employer;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Models.UserConfirmation;
import com.ozgurbaybas.Repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private UserActivationService userActivationService;
    private UserConfirmationService userConfirmationService;
    private CompanyStaffService companyStaffService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository,	UserActivationService userActivationService, UserConfirmationService userConfirmationService, CompanyStaffService companyStaffService) {
        this.employerRepository = employerRepository;
        this.userActivationService = userActivationService;
        this.companyStaffService = companyStaffService;
        this.userConfirmationService = userConfirmationService;
    }

    @Override
    public Result add(Employer employer) {

        if (!checkIfDomainsMatch(employer.getWebAddress(), employer.getEmail())) {
            return new ErrorResult("Web adresi ile e-posta aynı alan adına sahip olmalıdır.");
        }

        employer.setActivated(false);
        employer.setConfirmed(false);
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
    public DataResult<List<Employer>> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed) {
        return new SuccessDataResult<List<Employer>>(employerRepository.getByIsActivatedAndIsConfirmed(isActivated, isConfirmed));
    }

    @Override
    public Result activate(String code) {

        UserActivation userActivation = userActivationService.getByCode(code).getData();

        if (userActivation == null) {
            return new ErrorResult("You entered an invalid code.");
        }

        Employer employer = getById(userActivation.getUser().getId()).getData();
        employer.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        update(employer);
        userActivationService.update(userActivation);
        return new SuccessResult("Your membership is in the approval phase.");
    }

    @Override
    public Result confirm(Integer employerId, Integer companyStaffId, boolean isConfirmed) {

        Employer employer =  getById(employerId).getData();
        CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();

        if (isConfirmed == false) {
            userActivationService.delete(userActivationService.getByUser(employer).getData());
            delete(employer);
            return new ErrorResult("Membership not confirmed.");
        }

        update(employer);
        employer.setConfirmed(isConfirmed);
        userConfirmationService.add(new UserConfirmation(employer, companyStaff));
        return new SuccessResult("Membership confirmed.");
    }

    private boolean checkIfDomainsMatch(String webAddress, String email) {

        String[] splitEmailArray = email.split("@");

        return webAddress.contains(splitEmailArray[1]);
    }
}