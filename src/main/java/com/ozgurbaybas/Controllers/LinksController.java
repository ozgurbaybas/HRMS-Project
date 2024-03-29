package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Models.Link;
import com.ozgurbaybas.Services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
@CrossOrigin
public class LinksController {

    private LinkService linkService;

    @Autowired
    public LinksController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Link link) {
        return linkService.add(link);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Link link) {
        return linkService.update(link);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam int id) {
        return linkService.delete(id);
    }

    @GetMapping("/getAll")
    public DataResult<List<Link>> getAll() {
        return linkService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Link> getById(@RequestParam int id) {
        return linkService.getById(id);
    }

}