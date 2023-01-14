package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Skill;
import com.ozgurbaybas.Services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin
public class SkillsController {

    private SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Skill skill) {
        return skillService.add(skill);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Skill skill) {
        return skillService.update(skill);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody Skill skill) {
        return skillService.delete(skill);
    }

    @GetMapping("/getAll")
    public DataResult<List<Skill>> getAll() {
        return skillService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Skill> getById(@RequestParam int id) {
        return skillService.getById(id);
    }

}