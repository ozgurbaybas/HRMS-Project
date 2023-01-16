package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Repository.CompanyStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyStaffServiceImpl implements CompanyStaffService {

    private CompanyStaffRepository companyStaffRepository;

    @Autowired
    public CompanyStaffServiceImpl(CompanyStaffRepository companyStaffRepository) {
        this.companyStaffRepository = companyStaffRepository;
    }

    @Override
    public Result add(CompanyStaff companyStaff) {

        companyStaffRepository.save(companyStaff);
        return new SuccessResult("Company personnel added.");
    }

    @Override
    public Result update(CompanyStaff companyStaff) {

        CompanyStaff c = getById(companyStaff.getId()).getData();

        companyStaff.setEmail(companyStaff.getEmail() == null || companyStaff.getEmail() == ""
                ? c.getEmail()
                : companyStaff.getEmail());
        companyStaff.setPassword(companyStaff.getPassword() == null || companyStaff.getPassword() == ""
                ? c.getPassword()
                : companyStaff.getPassword());
        companyStaff.setFirstName(companyStaff.getFirstName() == null || companyStaff.getFirstName() == ""
                ? c.getFirstName()
                : companyStaff.getFirstName());
        companyStaff.setLastName(companyStaff.getLastName() == null || companyStaff.getLastName() == ""
                ? c.getLastName()
                : companyStaff.getLastName());

        companyStaffRepository.save(companyStaff);
        return new SuccessResult("Company staff updated.");
    }

    @Override
    public Result delete(int id) {

        companyStaffRepository.deleteById(id);
        return new SuccessResult("Company personnel deleted.");
    }

    @Override
    public DataResult<List<CompanyStaff>> getAll() {
        return new SuccessDataResult<List<CompanyStaff>>(companyStaffRepository.findAll());
    }

    @Override
    public DataResult<CompanyStaff> getById(int id) {
        return new SuccessDataResult<CompanyStaff>(companyStaffRepository.getById(id));
    }

}