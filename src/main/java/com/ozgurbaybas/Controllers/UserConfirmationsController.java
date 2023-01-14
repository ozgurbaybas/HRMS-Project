package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.UserConfirmation;
import com.ozgurbaybas.Services.UserConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userConfirmations")
@CrossOrigin
public class UserConfirmationsController {

    private UserConfirmationService userConfirmationService;

    @Autowired
    public UserConfirmationsController(UserConfirmationService userConfirmationService) {
        this.userConfirmationService = userConfirmationService;
    }

    @GetMapping("/getAll")
    public DataResult<List<UserConfirmation>> getAll() {
        return userConfirmationService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<UserConfirmation> getById(@RequestParam int id) {
        return userConfirmationService.getById(id);
    }

    @GetMapping("/getAllByUserId")
    DataResult<List<UserConfirmation>> getAllByUserId(@RequestParam int userId) {
        return userConfirmationService.getAllByUserId(userId);
    }
}