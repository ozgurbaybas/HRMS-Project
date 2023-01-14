package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.WorkingType;
import com.ozgurbaybas.Services.WorkingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workingTypes")
@CrossOrigin
public class WorkingTypesController {

    private WorkingTypeService workingTypeService;

    @Autowired
    public WorkingTypesController(WorkingTypeService workingTypeService) {
        this.workingTypeService = workingTypeService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody WorkingType workingType) {
        return workingTypeService.add(workingType);
    }

    @PutMapping("/update")
    public Result update(@RequestBody WorkingType workingType) {
        return workingTypeService.update(workingType);
    }

    @GetMapping("/getAll")
    public DataResult<List<WorkingType>> getAll() {
        return workingTypeService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<WorkingType> getById(@RequestParam int id) {
        return workingTypeService.getById(id);
    }

}