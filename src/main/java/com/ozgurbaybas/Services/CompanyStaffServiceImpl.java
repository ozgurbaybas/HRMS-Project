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
        return new SuccessResult("Şirket personeli eklendi.");
    }

    @Override
    public Result update(CompanyStaff companyStaff) {

        companyStaffRepository.save(companyStaff);
        return new SuccessResult("Şirket personeli güncellendi.");
    }

    @Override
    public Result delete(CompanyStaff companyStaff) {

        companyStaffRepository.delete(companyStaff);
        return new SuccessResult("Şirket personeli silindi.");
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