package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.City;

import java.util.List;

public interface CityService {

    Result add(City city);
    Result update(City city);
    Result delete(City city);
    DataResult<List<City>> getAll();
    DataResult<City> getById(int id);

}