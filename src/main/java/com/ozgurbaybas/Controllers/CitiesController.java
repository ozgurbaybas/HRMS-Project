package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.City;
import com.ozgurbaybas.Services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
public class CitiesController {

    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        cityService = cityService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody City city) {
        return cityService.add(city);
    }

    @PutMapping("/update")
    public Result update(@RequestBody City city) {
        return cityService.update(city);
    }

    @GetMapping("/getAll")
    public DataResult<List<City>> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<City> getById(@RequestParam int id) {
        return cityService.getById(id);
    }
}