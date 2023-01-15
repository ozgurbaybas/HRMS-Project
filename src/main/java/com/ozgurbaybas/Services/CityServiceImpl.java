package com.ozgurbaybas.Services;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Models.City;
import com.ozgurbaybas.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Result add(City city) {

        cityRepository.save(city);
        return new SuccessResult("City added.");
    }

    @Override
    public Result update(City city) {

        cityRepository.save(city);
        return new SuccessResult("The city has been updated.");
    }

    @Override
    public Result delete(int id) {

        cityRepository.deleteById(id);
        return new SuccessResult("The city has been deleted.");
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(cityRepository.findAll());
    }

    @Override
    public DataResult<City> getById(int id) {
        return new SuccessDataResult<City>(cityRepository.getById(id));
    }
}