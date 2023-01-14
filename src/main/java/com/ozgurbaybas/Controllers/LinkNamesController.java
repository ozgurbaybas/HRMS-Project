package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.LinkName;
import com.ozgurbaybas.Services.LinkNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linkNames")
@CrossOrigin
public class LinkNamesController {

    private LinkNameService linkNameService;

    @Autowired
    public LinkNamesController(LinkNameService linkNameService) {
        this.linkNameService = linkNameService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody LinkName linkName) {
        return linkNameService.add(linkName);
    }

    @PostMapping("/update")
    public Result update(@RequestBody LinkName linkName) {
        return linkNameService.update(linkName);
    }

    @GetMapping("/getAll")
    public DataResult<List<LinkName>> getAll() {
        return linkNameService.getAll();
    }

    @GetMapping("getById")
    public DataResult<LinkName> getById(@RequestParam int id) {
        return linkNameService.getById(id);
    }

}