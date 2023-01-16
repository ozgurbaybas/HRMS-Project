package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Employer;

import java.util.List;

public interface EmployerService extends BaseEntityService<Employer> {

    Result activate(String code);
    Result confirm(int employerId, int companyStaffId, int userConfirmationTypeId, boolean isConfirmed);
    DataResult<List<Employer>> getAllOnesThatWaitingForAccountConfirmation();
    DataResult<List<Employer>> getAllOnesThatWaitingForUpdateConfirmation();
    DataResult<List<Employer>> getAllByIsActivated(boolean isActivated);
    DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeId(boolean isConfirmed, int userConfirmationTypeId);
    DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeIdSortedByCompanyName(boolean isConfirmed, int userConfirmationTypeId);
    DataResult<Employer> getOneThatWaitingForUpdateConfirmationById(int id);

}