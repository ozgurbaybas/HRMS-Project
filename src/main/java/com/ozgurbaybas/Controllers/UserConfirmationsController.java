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
public class UserConfirmationsController {

    private UserConfirmationService userConfirmationService;

    @Autowired
    public UserConfirmationsController(UserConfirmationService userConfirmationService) {
        this.userConfirmationService = userConfirmationService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserConfirmation userConfirmation) {
        return userConfirmationService.add(userConfirmation);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserConfirmation userConfirmation) {
        return userConfirmationService.update(userConfirmation);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody UserConfirmation userConfirmation) {
        return userConfirmationService.delete(userConfirmation);
    }

    @GetMapping("/getAll")
    public DataResult<List<UserConfirmation>> getAll() {
        return userConfirmationService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<UserConfirmation> getById(@RequestParam int id) {
        return userConfirmationService.getById(id);
    }
}