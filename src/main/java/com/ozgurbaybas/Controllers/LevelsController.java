package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Level;
import com.ozgurbaybas.Services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelsController {

    private LevelService levelService;

    @Autowired
    public LevelsController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Level level) {
        return levelService.add(level);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Level level) {
        return levelService.update(level);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Level level) {
        return levelService.delete(level);
    }

    @GetMapping("/getAll")
    public DataResult<List<Level>> getAll() {
        return levelService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Level> getById(@RequestParam int id) {
        return levelService.getById(id);
    }

}