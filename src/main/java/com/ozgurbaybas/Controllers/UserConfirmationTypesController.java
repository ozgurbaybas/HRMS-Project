package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserConfirmationType;
import com.ozgurbaybas.Services.UserConfirmationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userConfirmationTypes")
@CrossOrigin
public class UserConfirmationTypesController {

    private UserConfirmationTypeService userConfirmationTypeService;

    @Autowired
    public UserConfirmationTypesController(UserConfirmationTypeService userConfirmationTypeService) {
        this.userConfirmationTypeService = userConfirmationTypeService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserConfirmationType userConfirmationType) {
        return userConfirmationTypeService.add(userConfirmationType);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserConfirmationType userConfirmationType) {
        return userConfirmationTypeService.update(userConfirmationType);
    }

    @GetMapping("/getAll")
    public DataResult<List<UserConfirmationType>> getAll() {
        return userConfirmationTypeService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<UserConfirmationType> getById(@RequestParam int id) {
        return userConfirmationTypeService.getById(id);
    }

}