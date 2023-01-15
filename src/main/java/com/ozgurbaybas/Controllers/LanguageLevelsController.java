package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.LanguageLevel;
import com.ozgurbaybas.Services.LanguageLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languageLevels")
@CrossOrigin
public class LanguageLevelsController {

    private LanguageLevelService languageLevelService;

    @Autowired
    public LanguageLevelsController(LanguageLevelService languageLevelService) {
        this.languageLevelService = languageLevelService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody LanguageLevel languageLevel) {
        return languageLevelService.add(languageLevel);
    }

    @PutMapping("/update")
    public Result update(@RequestBody LanguageLevel languageLevel) {
        return languageLevelService.update(languageLevel);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam int id) {
        return languageLevelService.delete(id);
    }

    @GetMapping("/getAll")
    public DataResult<List<LanguageLevel>> getAll() {
        return languageLevelService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<LanguageLevel> getById(@RequestParam int id) {
        return languageLevelService.getById(id);
    }

}