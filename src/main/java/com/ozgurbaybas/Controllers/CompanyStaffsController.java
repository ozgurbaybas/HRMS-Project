package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.CompanyStaff;
import com.ozgurbaybas.Services.CompanyStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companyStaffs")
public class CompanyStaffsController {

    private CompanyStaffService companyStaffService;

    @Autowired
    public CompanyStaffsController(CompanyStaffService companyStaffService) {
        this.companyStaffService = companyStaffService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CompanyStaff companyStaff) {
        return companyStaffService.add(companyStaff);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CompanyStaff companyStaff) {
        return companyStaffService.update(companyStaff);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody CompanyStaff companyStaff) {
        return companyStaffService.delete(companyStaff);
    }

    @GetMapping("/getAll")
    public DataResult<List<CompanyStaff>> getAll() {
        return companyStaffService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<CompanyStaff> getById(@RequestParam int id) {
        return companyStaffService.getById(id);
    }
}