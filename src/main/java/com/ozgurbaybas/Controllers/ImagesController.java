package com.ozgurbaybas.Controllers;

import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    private ImageService imageService;

    @Autowired
    public ImagesController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/getAll")
    public DataResult<List<Image>> getAll() {
        return imageService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<Image> getById(@RequestParam int id) {
        return imageService.getById(id);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam int userId, @RequestParam MultipartFile file) {
        return imageService.upload(userId, file);
    }

}