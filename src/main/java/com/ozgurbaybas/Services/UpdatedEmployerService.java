package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Models.UpdatedEmployer;

public interface UpdatedEmployerService extends BaseEntityService<UpdatedEmployer> {

    DataResult<UpdatedEmployer> getByEmployerId(int employerId);

}