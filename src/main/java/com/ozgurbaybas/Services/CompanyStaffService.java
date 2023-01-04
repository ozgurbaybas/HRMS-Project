package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.CompanyStaff;

import java.util.List;

public interface CompanyStaffService {

    Result add(CompanyStaff companyStaff);
    Result update(CompanyStaff companyStaff);
    Result delete(CompanyStaff companyStaff);
    DataResult<List<CompanyStaff>> getAll();
    DataResult<CompanyStaff> getById(int id);

}