package com.ozgurbaybas.Services;


import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.*;
import com.ozgurbaybas.Repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private UserService userService;
    private UserActivationService userActivationService;
    private UserConfirmationService userConfirmationService;
    private CompanyStaffService companyStaffService;
    private UpdatedEmployerService updatedEmployerService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, UserService userService, UserActivationService userActivationService,
                           UserConfirmationService userConfirmationService, CompanyStaffService companyStaffService, UpdatedEmployerService updatedEmployerService) {
        this.employerRepository = employerRepository;
        this.userService = userService;
        this.userActivationService = userActivationService;
        this.userConfirmationService = userConfirmationService;
        this.companyStaffService = companyStaffService;
        this.updatedEmployerService = updatedEmployerService;
    }

    @Override
    public Result add(Employer employer) {

        validateEmployer(employer);
        employer.setActivated(false);
        employer.setConfirmed(false);
        employerRepository.save(employer);
        return userActivationService.add(new UserActivation(employer));
    }
    @Override
    public Result update(Employer employer) {

        validateEmployer(employer);

        Employer employerInConfirmationProcess = getById(employer.getId()).getData();
        employerInConfirmationProcess.setConfirmed(false);

        UpdatedEmployer updatedEmployer = new UpdatedEmployer(
                0,
                employer.getEmail(),
                employer.getPassword(),
                employer.getCompanyName(),
                employer.getWebAddress(),
                employer.getPhoneNumber(),
                employer
        );

        updatedEmployerService.add(updatedEmployer);
        return new SuccessResult("The employer update is in the approval phase.");
    }

    @Override
    public Result delete(Employer employer) {

        employerRepository.delete(employer);
        return new SuccessResult("Employer deleted.");
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
    public Result activate(String code) {

        UserActivation userActivation = userActivationService.getByCode(code).getData();
        if (userActivation == null) {
            return new ErrorResult("You entered an invalid code.");
        }
        Employer employer = getById(userActivation.getUser().getId()).getData();
        employer.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        employerRepository.save(employer);
        userActivationService.update(userActivation);
        return new SuccessResult("Your membership is in the approval phase.");
    }
    @Override
    public Result confirm(int employerId, int companyStaffId, boolean isConfirmed) {

        Employer employer = getById(employerId).getData();
        CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
        UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(employerId).getData();

        int numberOfUserConfirmations = userConfirmationService.getAllByUserId(employerId).getData().size();

        if (!isConfirmed && numberOfUserConfirmations == 0) {
            userActivationService.delete(userActivationService.getByUserId(employer.getId()).getData());
            delete(employer);
            return new ErrorResult("Employer not approved.");
        }

        if (isConfirmed && numberOfUserConfirmations == 0) {
            employer.setConfirmed(isConfirmed);
            employerRepository.save(employer);
            userConfirmationService.add(new UserConfirmation(employer, companyStaff));
            return new SuccessResult("Employer approved.");
        }

        if (!isConfirmed && numberOfUserConfirmations > 0) {
            return new ErrorResult("Employer not approved.");
        }

        employer.setEmail(updatedEmployer.getEmail());
        employer.setPassword(updatedEmployer.getPassword());
        employer.setCompanyName(updatedEmployer.getCompanyName());
        employer.setWebAddress(updatedEmployer.getWebAddress());
        employer.setPhoneNumber(updatedEmployer.getCompanyName());
        employer.setConfirmed(isConfirmed);

        employerRepository.save(employer);
        updatedEmployerService.delete(updatedEmployer);
        userConfirmationService.add(new UserConfirmation(employer, companyStaff));
        return new SuccessResult("Employer approved.");
    }

    @Override
    public DataResult<List<Employer>> getAllByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed) {
        return new SuccessDataResult<List<Employer>>(employerRepository.getByIsActivatedAndIsConfirmed(isActivated, isConfirmed));
    }

    private boolean checkIfEmailExists(String email) {

        boolean result = false;

        if (userService.getByEmail(email).getData() == null) {
            result = true;
        }

        return result;
    }

    private boolean checkIfDomainsMatch(String webAddress, String email) {

        String[] splitEmailArray = email.split("@");

        return webAddress.contains(splitEmailArray[1]);
    }

    private Result validateEmployer(Employer employer) {

        if (!checkIfEmailExists(employer.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }

        if (!checkIfDomainsMatch(employer.getWebAddress(), employer.getEmail())) {
            return new ErrorResult("The web address and the e-mail must have the same domain name.");
        }

        return null;
    }

}