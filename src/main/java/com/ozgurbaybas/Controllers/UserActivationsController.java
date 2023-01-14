package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserActivation;
import com.ozgurbaybas.Services.UserActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userActivations")
@CrossOrigin
public class UserActivationsController {

    private UserActivationService userActivationService;

    @Autowired
    public UserActivationsController(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @GetMapping("/getAll")
    public DataResult<List<UserActivation>> getAll() {
        return userActivationService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<UserActivation> getById(@RequestParam int id) {
        return userActivationService.getById(id);
    }

}