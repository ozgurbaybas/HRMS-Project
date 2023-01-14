package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Language;
import com.ozgurbaybas.Services.LanguageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin
public class LanguagesController {

    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Language language) {
        return languageService.add(language);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Language language) {
        return languageService.update(language);
    }

    @GetMapping("/getAll")
    public DataResult<List<Language>> getAll() {
        return languageService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Language> getById(@RequestParam int id) {
        return languageService.getById(id);
    }

}