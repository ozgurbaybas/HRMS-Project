package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.*;
import com.ozgurbaybas.Models.*;
import com.ozgurbaybas.Repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private UserService userService;
    private UserActivationService userActivationService;
    private UserConfirmationService userConfirmationService;
    private UserConfirmationTypeService userConfirmationTypeService;
    private CompanyStaffService companyStaffService;
    private UpdatedEmployerService updatedEmployerService;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, UserService userService, UserActivationService userActivationService,
                           UserConfirmationService userConfirmationService, UserConfirmationTypeService userConfirmationTypeService, CompanyStaffService companyStaffService, UpdatedEmployerService updatedEmployerService) {
        this.employerRepository = employerRepository;
        this.userService = userService;
        this.userActivationService = userActivationService;
        this.userConfirmationService = userConfirmationService;
        this.userConfirmationTypeService = userConfirmationTypeService;
        this.companyStaffService = companyStaffService;
        this.updatedEmployerService = updatedEmployerService;
    }
    @Override
    public Result add(Employer employer) {
        validateEmployer(employer);
        employerRepository.save(employer);
        return userActivationService.add(new UserActivation(employer));
    }

    @Override
    public Result update(Employer employer) {
        validateEmployer(employer);
        Employer employerInConfirmationProcess = getById(employer.getId()).getData();
        UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(employer.getId()).getData();

        String email = employer.getEmail() == null ? employerInConfirmationProcess.getEmail() : employer.getEmail();
        String password = employer.getPassword() == null ? employerInConfirmationProcess.getPassword() : employer.getPassword();
        String companyName = employer.getCompanyName() == null ? employerInConfirmationProcess.getCompanyName() : employer.getCompanyName();
        String webAddress = employer.getWebAddress() == null ? employerInConfirmationProcess.getWebAddress() : employer.getWebAddress();
        String phoneNumber = employer.getPhoneNumber() == null ? employerInConfirmationProcess.getPhoneNumber() : employer.getPhoneNumber();

        if (updatedEmployer == null) {
            updatedEmployer = new UpdatedEmployer(0, email, password, companyName, webAddress, phoneNumber,	employer);
        } else {
            updatedEmployer.setEmail(email);
            updatedEmployer.setPassword(password);
            updatedEmployer.setCompanyName(companyName);
            updatedEmployer.setWebAddress(webAddress);
            updatedEmployer.setPhoneNumber(phoneNumber);
        }
        updatedEmployerService.add(updatedEmployer);
        return new SuccessResult("The employer update is in the approval phase.");
    }

    @Override
    public Result delete(int id) {
        employerRepository.deleteById(id);
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
        userActivation.setActivated(true);
        userActivation.setIsActivatedDate(LocalDate.now());

        userActivationService.update(userActivation);
        return new SuccessResult("Your membership is in the approval phase.");
    }

    @Override
    public Result confirm(int employerId, int companyStaffId, int userConfirmationTypeId, boolean isConfirmed) {
        Employer employer = getById(employerId).getData();
        CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
        UserConfirmationType userConfirmationType = userConfirmationTypeService.getById(userConfirmationTypeId).getData();
        UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(employerId).getData();
        if (!isConfirmed && userConfirmationTypeId == 1) {
            userActivationService.delete(userActivationService.getByUserId(employer.getId()).getData().getId());
            delete(employer.getId());
            return new ErrorResult("Employer account disapproved.");
        }
        if (isConfirmed && userConfirmationTypeId == 1) {
            userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
            return new SuccessResult("Employer account confirmed.");
        }

        if (!isConfirmed && userConfirmationTypeId == 2) {
            userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
            updatedEmployerService.delete(updatedEmployer.getId());
            return new ErrorResult("Employer update not approved.");
        }

        employer.setEmail(updatedEmployer.getEmail());
        employer.setPassword(updatedEmployer.getPassword());
        employer.setCompanyName(updatedEmployer.getCompanyName());
        employer.setWebAddress(updatedEmployer.getWebAddress());
        employer.setPhoneNumber(updatedEmployer.getPhoneNumber());
        employerRepository.save(employer);
        updatedEmployerService.delete(updatedEmployer.getId());
        userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
        return new SuccessResult("Employer update approved.");
    }

    @Override
    public DataResult<List<Employer>> getAllOnesThatWaitingForAccountConfirmation() {

        List<Employer> result = new ArrayList<Employer>();
        List<Employer> activatedEmployers = getAllByIsActivated(true).getData();

        for (Employer employer : activatedEmployers) {
            if (userConfirmationService.getAllByUserId(employer.getId()).getData().size() == 0) {
                result.add(employer);
            }
        }
        return new SuccessDataResult<List<Employer>>(result);
    }

    @Override
    public DataResult<List<Employer>> getAllOnesThatWaitingForUpdateConfirmation() {

        List<Employer> result = new ArrayList<Employer>();
        List<UpdatedEmployer> updatedEmployers = updatedEmployerService.getAll().getData();

        for (UpdatedEmployer updatedEmployer : updatedEmployers) {
            result.add(new Employer(updatedEmployer.getEmployer().getId() ,updatedEmployer.getEmail(), updatedEmployer.getPassword() ,updatedEmployer.getCompanyName(), updatedEmployer.getWebAddress(), updatedEmployer.getPhoneNumber()));
        }

        return new SuccessDataResult<List<Employer>>(result);
    }

    @Override
    public DataResult<List<Employer>> getAllByIsActivated(boolean isActivated) {
        return new SuccessDataResult<List<Employer>>(employerRepository.getByUserActivation_IsActivated(isActivated));
    }

    @Override
    public DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeId(boolean isConfirmed, int userConfirmationTypeId) {
        return new SuccessDataResult<List<Employer>>(employerRepository.getByUserConfirmations_IsConfirmedAndUserConfirmations_UserConfirmationType_Id(isConfirmed, userConfirmationTypeId));
    }

    @Override
    public DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeIdSortedByCompanyName(boolean isConfirmed, int userConfirmationTypeId) {

        Sort sort = Sort.by(Sort.Direction.ASC, "companyName");

        return new SuccessDataResult<List<Employer>>(employerRepository.getByUserConfirmations_IsConfirmedAndUserConfirmations_UserConfirmationType_Id(isConfirmed, userConfirmationTypeId, sort));
    }

    @Override
    public DataResult<Employer> getOneThatWaitingForUpdateConfirmationById(int id) {

        UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(id).getData();
        Employer result = new Employer(updatedEmployer.getEmployer().getId() ,updatedEmployer.getEmail(), updatedEmployer.getPassword() ,updatedEmployer.getCompanyName(), updatedEmployer.getWebAddress(), updatedEmployer.getPhoneNumber());

        return new SuccessDataResult<Employer>(result);
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

        if (employer.getEmail() == null || employer.getPassword() == null || employer.getCompanyName() == null || employer.getWebAddress() == null || employer.getPhoneNumber() == null ) {
            return null;
        }
        if (!checkIfEmailExists(employer.getEmail())) {
            return new ErrorResult("The e-mail address entered belongs to another account.");
        }
        if (!checkIfDomainsMatch(employer.getWebAddress(), employer.getEmail())) {
            return new ErrorResult("The web address and the e-mail must have the same domain name.");
        }

        return null;
    }

}